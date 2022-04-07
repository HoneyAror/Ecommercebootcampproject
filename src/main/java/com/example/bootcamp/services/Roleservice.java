package com.example.bootcamp.services;

import com.example.bootcamp.dto.SellerDTO;
import com.example.bootcamp.entities.Role;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.repos.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bootcamp.constants.Appconstants.ROLE_SELLER;

@Slf4j
@Service
public class Roleservice {

    @Autowired
    private RoleRepository roleRepository;

    public void save(String authority){
        if(roleRepository.findByAuthority(authority)==null){
            log.info("-??????????????????????????????????????????????????????????????????????????????????????????");
            roleRepository.save(new Role(authority));
        }
    }

//    public List<SellerDTO> getSellerData(){
//        List<Seller> sellers= (List<Seller>) roleRepository.findByAuthority(ROLE_SELLER);
//        return sellers.stream().map(o->new SellerDTO(o.getUser().getEmail(),o.getUser().getFirstName(),o.getUser().getMiddleName(),o.getUser().getLastName(),o.getUser().getPassword(),o.getUser().getConfirmpassword(),o.getUser().isActive(),o.getUser().isDeleted(),o.getUser().isLocked(),o.getUser().isExpired(),o.getUser().getInvalidAttemptCount(),o.getGst(),o.getCompanyContact(),o.getCompanyName())).collect(Collectors.toList());
//    }

}
