package com.example.bootcamp.services;

import com.example.bootcamp.dto.CustomerDTO;
import com.example.bootcamp.entities.ConfirmationToken;
import com.example.bootcamp.entities.Customer;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.repos.ConfirmationTokenRepository;
import com.example.bootcamp.repos.CustomerRepository;
import com.example.bootcamp.repos.RoleRepository;
import com.example.bootcamp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

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

    public void activateCustomer(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            user.setActive(true);
            userRepository.save(user);
        } else {
            System.out.println("token invalid");
        }
    }

}
