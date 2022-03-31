package com.example.bootcamp.services;

import com.example.bootcamp.dto.SellerDTO;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.repos.RoleRepository;
import com.example.bootcamp.repos.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class Sellerservice {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public Seller saveSeller(SellerDTO sellerTo){
        User userseller=new User();
        userseller.setEmail(sellerTo.getEmail());
        userseller.setPassword(sellerTo.getPassword());
        userseller.setFirstName(sellerTo.getFirstName());
        userseller.setMiddleName(sellerTo.getMiddleName());
        userseller.setLastName(sellerTo.getLastName());
        userseller.setDeleted(sellerTo.isDeleted());
        userseller.setActive(sellerTo.isActive());
        userseller.setExpired(sellerTo.isExpired());
        userseller.setLocked(sellerTo.isLocked());
        userseller.setInvalidAttemptCount(sellerTo.getInvalidAttemptCount());
        userseller.setRoles(Collections.singleton(roleRepository.findByAuthority("ROLE_SELLER")));
//        userseller.setAddresses(Collections.singleton(sellerTo.getAddress()));
        Seller seller=new Seller();
        seller.setGst(sellerTo.getGst());
        seller.setCompanyContact(sellerTo.getCompanyContact());
        seller.setCompanyName(sellerTo.getCompanyName());
        seller.setUser(userseller);
        return sellerRepository.save(seller);
    }





}
