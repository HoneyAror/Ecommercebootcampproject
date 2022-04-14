package com.example.bootcamp.controller;

import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForgotPasswordController {

    @Autowired
    private Userservice userservice;

    @PostMapping(value = "/forgot/password")
    public ResponseDTO forgotPassword(@RequestParam(required = true) String email){
        return new ResponseDTO(HttpStatus.OK,userservice.forgotPassword(email),"SUCCESS");
    }

    @PatchMapping(value = "/reset-password")
    public ResponseDTO resetPassword(@RequestHeader String confirmationToken, @RequestParam String password, @RequestParam String confirmPassword){
        return new ResponseDTO(HttpStatus.OK,userservice.resetPassword(confirmationToken,password,confirmPassword),"SUCCESS");
    }


}
