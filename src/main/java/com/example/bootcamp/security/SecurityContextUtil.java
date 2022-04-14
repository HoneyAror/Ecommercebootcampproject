package com.example.bootcamp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityContextUtil{
    //The SecurityContext is used to store the details of the currently authenticated user, also known as a principle.
    public static String findAuthenticatedUser(){
        Object principal=null;
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            return  null;
        }
        else {
            principal=authentication.getPrincipal();
        }
        if(principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        else {
            return null;
        }

    }
}
