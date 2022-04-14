package com.example.bootcamp.services;

import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entities.ConfirmationToken;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.exceptions.UserNotFoundException;
import com.example.bootcamp.repos.ConfirmationTokenRepository;
import com.example.bootcamp.repos.RoleRepository;
import com.example.bootcamp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Userservice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;


    public void saveAdmin(UserDTO userTo){
        User admin=new User();
        admin.setEmail(userTo.getEmail());
        admin.setFirstName(userTo.getFirstName());
        admin.setMiddleName(userTo.getMiddleName());
        admin.setLastName(userTo.getLastName());
        admin.setPassword(passwordEncoder.encode(userTo.getPassword()));
        admin.setConfirmpassword(passwordEncoder.encode(userTo.getConfirmpassword()));
        admin.setDeleted(userTo.isDeleted());
        admin.setActive(userTo.isActive());
        admin.setExpired(userTo.isExpired());
        admin.setLocked(userTo.isLocked());
        admin.setInvalidAttemptCount(userTo.getInvalidAttemptCount());
        admin.setRoles(Collections.singleton(roleRepository.findByAuthority("ROLE_ADMIN")));
        String password = userTo.getPassword();
        String confirmPassword = userTo.getConfirmpassword();
        if (Objects.equals(password,confirmPassword )) {
            userRepository.save(admin);
        }
        System.out.println("Total users saved::" + userRepository.count());
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDTO> getUserData() {
        List<User> users = userRepository.findAll();
        return users.stream().map(o-> new UserDTO(o.getEmail(), o.getFirstName(), o.getMiddleName(),o.getLastName(),o.getPassword(),o.getConfirmpassword(),o.isActive(),o.isDeleted(), o.isExpired(),o.isLocked(),o.getInvalidAttemptCount())).collect(Collectors.toList());
    }

    public String forgotPassword(String email) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent() && user.get().isActive() == true) {
//            ConfirmationToken oldToken=confirmationTokenRepository.findByUserId(user.get().getId());
//            confirmationTokenRepository.delete(oldToken);
            ConfirmationToken confirmationToken = new ConfirmationToken(user.get());
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.get().getEmail());
            mailMessage.setSubject("Forgot Password!");
            mailMessage.setText("To reset your password, please click here : "
                    + "http://localhost:8080/reset-password?token=" + confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);
            return ("ACTIVATION LINK SEND SUCCESSFULLY");
        } else {
            throw new UserNotFoundException("USER NOT FOUND");
        }
    }

    public boolean resetPassword(String confirmationToken, String password,String confirmPassword) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            if(password.equals(confirmPassword)){
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
                confirmationTokenRepository.delete(token);
                System.out.println("PASSWORD RESET SUCCESSFULLY");
                return  true;
            }
            else {
                System.out.println("New Password and Confirm Password do not match");
            }
        } else {
            System.out.println("token invalid");
        }

        return false;
    }


}
