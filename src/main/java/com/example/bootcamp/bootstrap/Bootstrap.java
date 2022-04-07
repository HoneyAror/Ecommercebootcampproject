package com.example.bootcamp.bootstrap;

import com.example.bootcamp.dto.CustomerDTO;
import com.example.bootcamp.dto.SellerDTO;
import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entities.Address;
import com.example.bootcamp.repos.UserRepository;
import com.example.bootcamp.services.Customerservice;
import com.example.bootcamp.services.Roleservice;
import com.example.bootcamp.services.Sellerservice;
import com.example.bootcamp.services.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static com.example.bootcamp.constants.Appconstants.*;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    private Roleservice roleservice;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Userservice userservice;

    @Autowired
    private Sellerservice sellerservice;

    @Autowired
    private Customerservice customerservice;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleservice.save(ROLE_ADMIN);
        roleservice.save(ROLE_SELLER);
        roleservice.save(ROLE_CUSTOMER);


        String email="aman.mishra@tothenew.com";
        System.out.println("Going to save admin");
        if(userRepository.findByEmail(email)==null){
            userservice.saveAdmin(new UserDTO("aman.mishra@tothenew.com","HONEY","KUMAR","ARORA","Honey@12345","Honey@12345",true,false,false,false,0));
        }

       // Address sellerAddress = new Address("New ", "Delhi", "India", "F-226", 110018, "Labell");
        email="seller@gmail.com";

        if(userRepository.findByEmail(email)==null){
            sellerservice.saveSeller(new SellerDTO(email,"VIJAY","SINGH","ARORA","Vijay@1233","Vijay@1233",true,false,false,false,0,"19AAACH6301A1Z8", "971354899999", "SellerCompany"));
        }

//        Set<Address> customerAddresses = new HashSet<>();
//        customerAddresses.add(new Address("New Delhi", "Delhi", "India", "F-226", 110018, "Labell"));
//        customerAddresses.add(new Address("Mumbai", "Delhi", "India", "F-226", 110018, "Labell"));

        email="customer@gmail.com";
        if(userRepository.findByEmail(email)==null){
            customerservice.saveCustomer(new CustomerDTO(email,"MEENU","KUMAR","ARORA","Meenu@144","Meenu@144",true,false,false,false,0,9866433333l));

        }




    }
}
