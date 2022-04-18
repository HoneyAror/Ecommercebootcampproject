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

    @PostMapping(value = "register/forgot/password")
    public ResponseDTO forgotPassword(@RequestParam(required = true) String email){
        return new ResponseDTO(HttpStatus.OK,userservice.forgotPassword(email),"SUCCESS");
    }

    @PatchMapping(value = "register/reset-password")
    public ResponseDTO resetPasswordSeller(@RequestHeader String confirmationToken, @RequestParam String password, @RequestParam String confirmPassword){
        return new ResponseDTO(HttpStatus.OK,userservice.resetPassword(confirmationToken,password,confirmPassword),"SUCCESS");
    }



}
