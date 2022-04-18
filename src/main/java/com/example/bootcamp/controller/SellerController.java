package com.example.bootcamp.controller;

import com.example.bootcamp.dto.AddressDTO;
import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.dto.SellerDTO;
import com.example.bootcamp.dto.UpdatePasswordDTO;
import com.example.bootcamp.services.Sellerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private Sellerservice sellerservice;

     @GetMapping(value = "/view/profile")
       public ResponseDTO getSellerProfile(){
           return new ResponseDTO(HttpStatus.OK,sellerservice.getSellerProfile(),"SUCCESS");
       }

       @PatchMapping(value = "/update")
      public  ResponseDTO updateSeller(@RequestBody SellerDTO sellerDTO){
     return  new ResponseDTO(HttpStatus.OK,sellerservice.updateSeller(sellerDTO),"SUCCESS");
       }

       @PatchMapping(value = "/update/password")
       public ResponseDTO updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
         return new ResponseDTO(HttpStatus.OK,sellerservice.updatePassword(updatePasswordDTO.getPassword()),"SUCCESS");
       }

       @PatchMapping(value = "/update/address")
    public ResponseDTO updateAddress(@RequestBody AddressDTO addressDTO,@RequestParam("id") Long id){
         return new ResponseDTO(HttpStatus.OK,sellerservice.updateAddress(addressDTO,id),"SUCCESS");
       }




}
