package com.example.bootcamp.controller;

import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.services.Sellerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {

    @Autowired
    private Sellerservice sellerservice;

     @GetMapping(value = "/view/profile")
       public ResponseDTO getSellerProfile(){
           return new ResponseDTO(HttpStatus.OK,sellerservice.getSellerProfile(),"SUCCESS");
       }

}
