package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CustomerDTO;
import com.example.bootcamp.dto.ResponseDTO;
import com.example.bootcamp.dto.SellerDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entities.ConfirmationToken;
import com.example.bootcamp.entities.Customer;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.services.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

private Logger logger=LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Userservice userservice;

    @Autowired
    private Sellerservice sellerservice;


    @Autowired
    private Customerservice customerservice;


    @Autowired
    private EmailService emailService;

//    @Autowired
//    private Roleservice roleservice;


    @GetMapping(value = "/internationalization")
    public String internationalization(){
        return messageSource.getMessage("good.morning.message",null,LocaleContextHolder.getLocale());
    }



    @GetMapping(value = "/users")
    public ResponseDTO getalluser() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(200);
        responseDTO.setMessage("Data returned successfully!");
        responseDTO.setData(userservice.getUserData());
        return responseDTO;
    }


    @PostMapping(value = "/seller")         //ResponseEntity wraps the original object as its body which is optional .If you want to return an object or null, ResponseEntity will work in either way.
    private ResponseEntity<Object> registerSeller(@Valid @RequestBody SellerDTO sellerDTO) {
        Seller sellerDTO1=sellerservice.saveSeller(sellerDTO);
        if(sellerDTO1 == null){
            return new ResponseEntity<>("Your Password and confirmPassword doesn't matched. " +
                    "\nPlease Check the confirm password and try again", HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>("Seller is Created Successfully!", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/customer")
    private ResponseEntity<Object> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
       Customer customerDTO1=customerservice.saveCustomer(customerDTO);
        if(customerDTO1 == null){
            return new ResponseEntity<>("Your Password and confirmPassword doesn't matched. " +
                    "\nPlease Check the confirm password and try again", HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>("Customer is Created Successfully!", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public void confirmAcount(@RequestParam  ("token") String confirmationToken) {
      customerservice.activateCustomer(confirmationToken);

    }


}



