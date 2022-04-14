package com.example.bootcamp.services;

import com.example.bootcamp.dto.CustomerDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entities.ConfirmationToken;
import com.example.bootcamp.entities.Customer;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.exceptions.UserNotFoundException;
import com.example.bootcamp.repos.ConfirmationTokenRepository;
import com.example.bootcamp.repos.CustomerRepository;
import com.example.bootcamp.repos.RoleRepository;
import com.example.bootcamp.repos.UserRepository;
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

    public Customer saveCustomer(CustomerDTO customerTo){
        User usercustomer=new User();
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
        Customer customer=new Customer();
        customer.setContact(customerTo.getContact());
        customer.setUser(usercustomer);
        String password = customerTo.getPassword();
        String confirmPassword = customerTo.getConfirmpassword();
        if (Objects.equals(password,confirmPassword )) {
            customerRepository.save(customer);
            ConfirmationToken confirmationToken = new ConfirmationToken(usercustomer);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(usercustomer.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

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
//            ConfirmationToken oldToken=confirmationTokenRepository.findByUserId(user.get().get);
//            confirmationTokenRepository.delete(oldToken);
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
        List<Customer> users = customerRepository.findAllCustomer(PageRequest.of(1,2, Sort.by("id")));
        return users.stream().map(o-> new CustomerDTO(o.getUser().getEmail(), o.getUser().getFirstName(), o.getUser().getMiddleName(),o.getUser().getLastName(),o.getUser().getPassword(),o.getUser().getConfirmpassword(),o.getUser().isActive(),o.getUser().isDeleted(), o.getUser().isExpired(),o.getUser().isLocked(),o.getUser().getInvalidAttemptCount(),o.getContact())).collect(Collectors.toList());
    }

    public String adminactivateCustomer(Long id) throws Exception {
        Optional<User> customer = userRepository.findById(id);
        if (customer.isPresent()) {
            if (customer.get().isActive( )== true) {
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
        }
        else {
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
        }
        else {
            throw new UserNotFoundException("CUSTOMER NOT FOUND");
        }
    }


}
