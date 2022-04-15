package com.example.bootcamp.services;

import com.example.bootcamp.dto.SellerDTO;
import com.example.bootcamp.dto.SellerResponseDTO;
import com.example.bootcamp.entities.Address;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.exceptions.CannotUpdateException;
import com.example.bootcamp.exceptions.UserNotFoundException;
import com.example.bootcamp.repos.RoleRepository;
import com.example.bootcamp.repos.SellerRepository;
import com.example.bootcamp.repos.UserRepository;
import com.example.bootcamp.util.SecurityContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
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

    @Autowired
    private Userservice userservice;

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
        userseller.setAddresses(Collections.singleton(sellerTo.getAddress()));
        Seller seller=new Seller();
        seller.setGst(sellerTo.getGst());
        seller.setCompanyContact(sellerTo.getCompanyContact());
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


    public SellerResponseDTO getSellerProfile(String email){
     SellerResponseDTO sellerResponseClass = new SellerResponseDTO();
     User user= SecurityContextHolderUtil.getCurrentUserEmail();
      //  User user =userRepository.findByEmail(email);
        if(user!=null) {
            Optional<Seller> seller1 = sellerRepository.findById(user.getId());
            if(seller1.isPresent()){
                Seller seller=seller1.get();
                sellerResponseClass.setId(seller.getUser().getId());
                sellerResponseClass.setEmail(seller.getUser().getEmail());
                sellerResponseClass.setFirstName(seller.getUser().getFirstName());
                sellerResponseClass.setMiddleName(seller.getUser().getMiddleName());
                sellerResponseClass.setLastName(seller.getUser().getLastName());
                sellerResponseClass.setActive(seller.getUser().isActive());
                sellerResponseClass.setGst(seller.getGst());
                sellerResponseClass.setCompanyName(seller.getCompanyName());
                sellerResponseClass.setCompanyContact(seller.getCompanyContact());
                // sellerResponseClass.setAddress(seller.getUser().)
            }
        }
        return sellerResponseClass;

    }

    public String updateSeller(SellerDTO sellerDTO ){
        //String email= SecurityContextHolderUtil.getCurrentUserEmail();
        User user=userRepository.findByEmail("");
        Seller seller=sellerRepository.findById(user.getId()).get();
        if (user.getEmail() != sellerDTO.getEmail())
            throw new CannotUpdateException("You cannot change email");
        if (sellerDTO.getPassword() != null)
            throw new CannotUpdateException("You cannot change Password\nTo change please hit /changePassword API");
        if (sellerDTO.getFirstName() != null)
            user.setFirstName(sellerDTO.getFirstName());
        if (sellerDTO.getMiddleName() != null)
            user.setMiddleName(sellerDTO.getMiddleName());
        if (sellerDTO.getLastName() !=null)
            user.setLastName(sellerDTO.getLastName());
        if (sellerDTO.getCompanyContact() != null)
            seller.setCompanyContact(sellerDTO.getCompanyContact());
        if (seller.getCompanyName() != null)
            seller.setCompanyName(sellerDTO.getCompanyName());
        if (sellerDTO.getGst() != null)
            seller.setGst(sellerDTO.getGst());
        userRepository.save(user);
        sellerRepository.save(seller);
        return "SELLER UPDATED!";
    }
//
    public String updatePassword(String password)
    {
        //String email = SecurityContextHolderUtil.getCurrentUserEmail();
        User user = userservice.findByEmail("");
        userservice.changePassword(user,password);
        return "PASSWORD CHANGED!";
    }





}
