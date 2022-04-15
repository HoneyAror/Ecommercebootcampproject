package com.example.bootcamp.util;

import com.example.bootcamp.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHolderUtil {

    public static User getCurrentUserEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || !authentication.isAuthenticated())
            return null;
        return ((User)authentication.getPrincipal());
    }
}
