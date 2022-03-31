package com.example.bootcamp.services;

import com.example.bootcamp.dto.CustomerDTO;
import com.example.bootcamp.entities.Customer;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.repos.CustomerRepository;
import com.example.bootcamp.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class Customerservice {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(CustomerDTO customerTo){
        User usercustomer=new User();
        usercustomer.setEmail(customerTo.getEmail());
        usercustomer.setFirstName(customerTo.getFirstName());
        usercustomer.setMiddleName(customerTo.getMiddleName());
        usercustomer.setLastName(customerTo.getLastName());
        usercustomer.setPassword(customerTo.getPassword());
        usercustomer.setDeleted(customerTo.isDeleted());
        usercustomer.setActive(customerTo.isActive());
        usercustomer.setExpired(customerTo.isExpired());
        usercustomer.setLocked(customerTo.isLocked());
        usercustomer.setInvalidAttemptCount(customerTo.getInvalidAttemptCount());
        usercustomer.setRoles(Collections.singleton(roleRepository.findByAuthority("ROLE_CUSTOMER")));
//        usercustomer.setAddresses(customerTo.getAddresses());
        Customer customer=new Customer();
        customer.setContact(customerTo.getContact());
        customer.setUser(usercustomer);
        return customerRepository.save(customer);
    }


}
