package com.example.bootcamp.security;

import com.example.bootcamp.entities.User;
import com.example.bootcamp.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
public class AppUserDetailService implements UserDetailsService{

    @Autowired
    private
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("Could not find user with that email");
        }
        user.setGrantedAuthorities(user.getRoles().stream().map(role -> role.getAuthority())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
        return (UserDetails) user;
    }
}