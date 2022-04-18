package com.example.bootcamp.controller;

import com.example.bootcamp.entities.User;
import com.example.bootcamp.util.SecurityContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LogoutController {

    @Autowired
    private TokenStore tokenStore;

    @PostMapping("/doLogout")
    public ResponseEntity<?> logOut(HttpServletRequest request) throws ServletException {
        User user= SecurityContextHolderUtil.getCurrentUserEmail();
        //email not found exception
        String token = request.getHeader("Authorization");
        String finalToken = token.replace("Bearer ", "").trim();
        tokenStore.removeAccessToken(tokenStore.readAccessToken(finalToken));
        return new ResponseEntity<String>("LOGGED OUT", HttpStatus.OK);

    }
}

