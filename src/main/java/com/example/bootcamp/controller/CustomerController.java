package com.example.bootcamp.controller;

import com.example.bootcamp.dto.*;
import com.example.bootcamp.services.Customerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private Customerservice customerservice;

    @GetMapping(value = "/view/profile")
    public ResponseDTO getCustomerProfile(){
        return new ResponseDTO(HttpStatus.OK,customerservice.getCustomerProfile(),"SUCCESS");
    }

    @PatchMapping(value = "/update")
    public  ResponseDTO updateSeller(@RequestBody CustomerDTO customerDTO){
        return  new ResponseDTO(HttpStatus.OK,customerservice.updateCustomer(customerDTO),"SUCCESS");
    }

    @PatchMapping(value = "/update/password")
    public ResponseDTO updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        return new ResponseDTO(HttpStatus.OK,customerservice.updatePassword(updatePasswordDTO.getPassword()),"SUCCESS");
    }

    @PatchMapping(value = "/update/address")
    public ResponseDTO updateAddress(@RequestBody AddressDTO addressDTO, @RequestParam("id") Long id){
        return new ResponseDTO(HttpStatus.OK,customerservice.updateAddress(addressDTO,id),"SUCCESS");
    }






}
