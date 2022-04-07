package com.example.bootcamp.services;

import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.repos.RoleRepository;
import com.example.bootcamp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class Userservice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public List<UserDTO> getUserData() {
        List<User> users = userRepository.findAll();
        return users.stream().map(o-> new UserDTO(o.getEmail(), o.getFirstName(), o.getMiddleName(),o.getLastName(),o.getPassword(),o.getConfirmpassword(),o.isActive(),o.isDeleted(), o.isExpired(),o.isLocked(),o.getInvalidAttemptCount())).collect(Collectors.toList());
    }

}
