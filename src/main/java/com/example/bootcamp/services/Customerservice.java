package com.example.bootcamp.services;

import com.example.bootcamp.dto.*;
import com.example.bootcamp.entities.*;
import com.example.bootcamp.exceptions.AddressNotFoundException;
import com.example.bootcamp.exceptions.UserNotFoundException;
import com.example.bootcamp.repos.*;
import com.example.bootcamp.util.SecurityContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Customerservice {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Userservice userservice;

    @Autowired
    private AddressRepository addressRepository;

    public Customer saveCustomer(CustomerDTO customerTo) {
        User usercustomer = new User();
        usercustomer.setEmail(customerTo.getEmail());
        usercustomer.setFirstName(customerTo.getFirstName());
        usercustomer.setMiddleName(customerTo.getMiddleName());
        usercustomer.setLastName(customerTo.getLastName());
        usercustomer.setPassword(passwordEncoder.encode(customerTo.getPassword()));
        usercustomer.setConfirmpassword(passwordEncoder.encode(customerTo.getConfirmpassword()));
        usercustomer.setDeleted(customerTo.isDeleted());
        usercustomer.setActive(customerTo.isActive());
        usercustomer.setExpired(customerTo.isExpired());
        usercustomer.setLocked(customerTo.isLocked());
        usercustomer.setInvalidAttemptCount(customerTo.getInvalidAttemptCount());
        usercustomer.setRoles(Collections.singleton(roleRepository.findByAuthority("ROLE_CUSTOMER")));
        userRepository.save(usercustomer);
        Customer customer = new Customer();
        customer.setContact(customerTo.getContact());
        customer.setUser(usercustomer);
        String password = customerTo.getPassword();
        Address address = new Address();
        address.setCity(customerTo.getAddress().getCity());
        address.setState(customerTo.getAddress().getState());
        address.setCountry(customerTo.getAddress().getCountry());
        address.setAddressLine(customerTo.getAddress().getAddressLine());
        address.setZipCode(customerTo.getAddress().getZipCode());
        address.setLabel(customerTo.getAddress().getLabel());
        address.setUser(usercustomer);
        addressRepository.save(address);
        String confirmPassword = customerTo.getConfirmpassword();
        if (Objects.equals(password, confirmPassword)) {
            customerRepository.save(customer);
            ConfirmationToken confirmationToken = new ConfirmationToken(usercustomer);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(usercustomer.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);
        }
        return customer;
    }

    public String activateCustomer(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setActive(true);
            userRepository.save(user);
            return ("CUSTOMER ACTIVATED SUCCESSFULLY");
        } else {
            return ("token invalid");
        }
    }


    public String resendActivation(String email) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent() && user.get().isActive() == false) {
            ConfirmationToken oldToken=confirmationTokenRepository.findByUserId(user.get().getId());
            confirmationTokenRepository.delete(oldToken);
            ConfirmationToken confirmationToken = new ConfirmationToken(user.get());
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.get().getEmail());
            mailMessage.setSubject("Activation Link!");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);
            return ("ACTIVATION LINK SEND SUCCESSFULLY");
        } else {
            throw new UserNotFoundException("USER NOT FOUND");
        }
    }

    public List<CustomerDTO> getCustomerData() {
        List<Customer> users = customerRepository.findAllCustomer(PageRequest.of(1, 2, Sort.by("id")));
        return users.stream().map(o -> new CustomerDTO(o.getUser().getEmail(), o.getUser().getFirstName(), o.getUser().getMiddleName(), o.getUser().getLastName(), o.getUser().getPassword(), o.getUser().getConfirmpassword(), o.getUser().isActive(), o.getUser().isDeleted(), o.getUser().isExpired(), o.getUser().isLocked(), o.getUser().getInvalidAttemptCount(), o.getContact())).collect(Collectors.toList());
    }

    public String adminactivateCustomer(Long id) throws Exception {
        Optional<User> customer = userRepository.findById(id);
        if (customer.isPresent()) {
            if (customer.get().isActive() == true) {
                return ("Customer Already active");
            } else {
                customer.get().setActive(true);
                userRepository.save(customer.get());
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(customer.get().getEmail());
                mailMessage.setSubject("Account Activation!");
                mailMessage.setText("YOUR ACCOUNT HAS BEEN ACTIVATED!");
                emailService.sendEmail(mailMessage);
                return ("Customer is Activated!");
            }
        } else {
            throw new UserNotFoundException("CUSTOMER NOT FOUND");
        }
    }

    public String admindeactivateCustomer(Long id) throws UserNotFoundException {
        Optional<User> customer = userRepository.findById(id);
        if (customer.isPresent()) {
            if (customer.get().isActive() == true) {
                customer.get().setActive(false);
                userRepository.save(customer.get());
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(customer.get().getEmail());
                mailMessage.setSubject("Account De-Activation!");
                mailMessage.setText("YOUR ACCOUNT HAS BEEN DE-ACTIVATED!");
                emailService.sendEmail(mailMessage);
                return ("Customer is De-Activated!");
            } else {
                return ("Customer is Already De-activated");
            }
        } else {
            throw new UserNotFoundException("CUSTOMER NOT FOUND");
        }
    }

    public CustomerResponseDTO getCustomerProfile() {
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        User user = SecurityContextHolderUtil.getCurrentUserEmail();
        if (user != null) {
            Optional<Customer> customer1 = customerRepository.findById(user.getId());
            if (customer1.isPresent()) {
                Customer customer = customer1.get();
                customerResponseDTO.setId(customer.getUser().getId());
                customerResponseDTO.setEmail(customer.getUser().getEmail());
                customerResponseDTO.setFirstName(customer.getUser().getFirstName());
                customerResponseDTO.setMiddleName(customer.getUser().getMiddleName());
                customerResponseDTO.setLastName(customer.getUser().getLastName());
                customerResponseDTO.setActive(customer.getUser().isActive());
                customerResponseDTO.setExpired(customer.getUser().isExpired());
                customerResponseDTO.setContact(customer.getContact());
                // sellerResponseClass.setAddress(seller.);
            }
        }
        return customerResponseDTO;

    }

    public String updateCustomer(CustomerDTO customerDTO) {
        User user = SecurityContextHolderUtil.getCurrentUserEmail();
        if (customerDTO.getFirstName() != null)
            user.setFirstName(customerDTO.getFirstName());
        if (customerDTO.getMiddleName() != null)
            user.setMiddleName(customerDTO.getMiddleName());
        if (customerDTO.getLastName() != null)
            user.setLastName(customerDTO.getLastName());
        if (customerDTO.getContact() != null)
            user.getCustomer().setContact(customerDTO.getContact());
        userRepository.save(user);
        return "CUSTOMER UPDATED!";
    }

    public String updatePassword(String password) {
        User user = SecurityContextHolderUtil.getCurrentUserEmail();
        userservice.changePassword(user, password);
        return "PASSWORD CHANGED!";
    }

    public void addAddress(AddressDTO address) {
        User user = SecurityContextHolderUtil.getCurrentUserEmail();
        user.getAddresses();
    }


    public String updateAddress(AddressDTO address, Long id) {
        Address address1 = addressRepository.getById(id);
        if (address1 != null) {
            if (address.getCity() != null)
                address1.setCity(address.getCity());
            if (address.getState() != null)
                address1.setState(address.getState());
            if (address.getCountry() != null)
                address1.setCountry(address.getCountry());
            if (address.getAddressLine() != null)
                address1.setAddressLine(address.getAddressLine());
            if (address.getZipCode() != null)
                address1.setZipCode(address.getZipCode());
            if (address.getLabel() != null)
                address1.setLabel(address.getLabel());
            addressRepository.save(address1);
            return ("ADDRESS UPDATED!");
        } else {
            throw new AddressNotFoundException("ADDRESS NOT FOUND");
        }


    }
}
