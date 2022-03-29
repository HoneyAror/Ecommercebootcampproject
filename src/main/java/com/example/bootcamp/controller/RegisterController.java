package com.example.bootcamp.controller;

import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.repos.CustomerRepository;
import com.example.bootcamp.repos.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/seller")
    public Seller registerseller(@RequestBody Seller seller){
        return sellerRepository.save(seller);
    }

    @GetMapping("/seller")
    public List<Seller> getallseller(){
        return sellerRepository.findAll();
    }
}
