package com.example.bootcamp.services;

import com.example.bootcamp.Response.SellerResponseClass;
import com.example.bootcamp.dto.CustomerDTO;
import com.example.bootcamp.dto.SellerDTO;
import com.example.bootcamp.entities.Address;
import com.example.bootcamp.entities.Customer;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.exceptions.UserNotFoundException;
import com.example.bootcamp.repos.RoleRepository;
import com.example.bootcamp.repos.SellerRepository;
import com.example.bootcamp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Sellerservice {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public Seller saveSeller(SellerDTO sellerTo){
        User userseller=new User();
        userseller.setEmail(sellerTo.getEmail());
        userseller.setPassword(passwordEncoder.encode(sellerTo.getPassword()));
        userseller.setConfirmpassword(passwordEncoder.encode(sellerTo.getConfirmpassword()));
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
        String password = sellerTo.getPassword();
        String confirmPassword = sellerTo.getConfirmpassword();
        if (Objects.equals(password,confirmPassword )) {
            return sellerRepository.save(seller);
        }else
            return null;
    }

    public List<SellerDTO> getSellerData() {
        List<Seller> users = sellerRepository.findAllSeller(PageRequest.of(2,2, Sort.by("id")));
        return users.stream().map(o-> new SellerDTO(o.getUser().getEmail(), o.getUser().getFirstName(), o.getUser().getMiddleName(),o.getUser().getLastName(),o.getUser().getPassword(),o.getUser().getConfirmpassword(),o.getUser().isActive(),o.getUser().isDeleted(), o.getUser().isExpired(),o.getUser().isLocked(),o.getUser().getInvalidAttemptCount(),o.getGst(),o.getCompanyContact(),o.getCompanyName())).collect(Collectors.toList());
    }


    public String adminactivateSeller(Long id) throws Exception {
        Optional<User> seller = userRepository.findById(id);
        if (seller != null) {
            if (seller.get().isActive() == true) {
                return ("Seller Already active");
            } else {
                seller.get().setActive(true);
                userRepository.save(seller.get());
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(seller.get().getEmail());
                mailMessage.setSubject("Account Activation!");
                mailMessage.setText("YOUR ACCOUNT HAS BEEN ACTIVATED!");
                emailService.sendEmail(mailMessage);
                return ("Seller is Activated!");
            }
        }
        else {
            throw new UserNotFoundException("SELLER NOT FOUND");
        }
    }

    public String admindeactivateSeller(Long id) throws Exception {
        Optional<User> seller= userRepository.findById(id);
        if (seller != null) {
            if (seller.get().isActive() == true) {
                seller.get().setActive(false);
                userRepository.save(seller.get());
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(seller.get().getEmail());
                mailMessage.setSubject("Account De-Activation!");
                mailMessage.setText("YOUR ACCOUNT HAS BEEN DE-ACTIVATED!");
                emailService.sendEmail(mailMessage);
                return ("Seller is De-Activated!");
            } else {
                return ("Seller is Already De-activated");
            }
        }
        else {
            throw new UserNotFoundException("SELLER NOT FOUND");
        }
    }


    public String getSellerProfile(){
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=userDetails.getUsername();
        User user=userRepository.findByEmail(username);
        Seller seller=sellerRepository.findById(user.getId()).get();
        SellerResponseClass sellerResponseClass=new SellerResponseClass();
        sellerResponseClass.setId(seller.getUser().getId());
        sellerResponseClass.setFirstName(seller.getUser().getFirstName());
        sellerResponseClass.setMiddleName(seller.getUser().getMiddleName());
        sellerResponseClass.setLastName(seller.getUser().getLastName());
        sellerResponseClass.setActive(seller.getUser().isActive());
        sellerResponseClass.setCompanyName(seller.getCompanyName());
        sellerResponseClass.setCompanyContact(seller.getCompanyContact());
        return ("hhhh");
    }





}
