package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CustomerDTO;
import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.dto.SellerDTO;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.repos.CustomerRepository;
import com.example.bootcamp.repos.SellerRepository;
import com.example.bootcamp.repos.UserRepository;
import com.example.bootcamp.services.Customerservice;
import com.example.bootcamp.services.Sellerservice;
import com.example.bootcamp.services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private Userservice userservice;

    @Autowired
    private Sellerservice sellerservice;


    @Autowired
    private Customerservice customerservice;


    @GetMapping(value = "/users")
    public ResponseDTO getalluser() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(200);
        responseDTO.setMessage("Data returned successfully!");
        responseDTO.setData(userservice.getUserData());
        return responseDTO;
    }

    @PostMapping(value = "/seller")
    private ResponseDTO registerseller(@RequestBody SellerDTO sellerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(200);
        responseDTO.setMessage("Seller created successfully!");
        responseDTO.setData(sellerservice.saveSeller(sellerDTO));
        return responseDTO;
    }

    @PostMapping(value = "/customer")
    private ResponseDTO registercustomer(@RequestBody CustomerDTO customerDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(200);
        responseDTO.setMessage("Customer created successfully!");
        responseDTO.setData(customerservice.saveCustomer(customerDTO));
        return responseDTO;
    }
}



