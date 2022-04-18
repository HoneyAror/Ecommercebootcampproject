package com.example.bootcamp.controller;

import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.services.Customerservice;
import com.example.bootcamp.services.Sellerservice;
import com.example.bootcamp.services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/admin")
public class AdminController {

   @Autowired
   private Customerservice customerservice;

   @Autowired
   private Sellerservice sellerservice;

   @Autowired
   private Userservice userservice;

    @GetMapping(value = "/view/Customers")
    public ResponseDTO getCustomers() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Data returned successfully!");
        responseDTO.setData(customerservice.getCustomerData());
        return responseDTO;
    }
    @GetMapping(value = "/view/Sellers")
    public ResponseDTO getSellers() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(HttpStatus.OK);
        responseDTO.setMessage("Data returned successfully!");
        responseDTO.setData(sellerservice.getSellerData());
        return responseDTO;
    }

    @PatchMapping("activate/Customer")
        public ResponseDTO activateCustomer(@RequestParam("id") Long id) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        return new ResponseDTO(HttpStatus.OK, customerservice.adminactivateCustomer(id),"SUCCESS");
    }

    @PatchMapping("deactivate/Customer")
    public ResponseDTO deactivateCustomer(@RequestParam("id") Long id) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        return new ResponseDTO(HttpStatus.OK,customerservice.admindeactivateCustomer(id),"SUCCESS");
    }

    @PatchMapping("activate/Seller")
    public ResponseDTO activateSeller(@RequestParam("id") Long id) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        return new ResponseDTO(HttpStatus.OK,sellerservice.adminactivateSeller(id),"SUCCESS");
    }

    @PatchMapping("deactivate/Seller")
    public ResponseDTO deactivateSeller(@RequestParam("id") Long id) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        return new ResponseDTO(HttpStatus.OK,sellerservice.admindeactivateSeller(id),"SUCCESS");
    }

    @PatchMapping("/user/lockorunlock/{user_id}")
    public ResponseDTO lockOrUnlockUser(@PathVariable("user_id") Long user_id,@RequestParam(name = "lock")Boolean lock) {
            return new ResponseDTO( HttpStatus.OK,userservice.lockOrUnlockUser(user_id,lock),"SUCCESS");
    }


}
