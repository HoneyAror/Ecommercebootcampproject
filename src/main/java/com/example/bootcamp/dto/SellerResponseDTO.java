package com.example.bootcamp.dto;

import com.example.bootcamp.entities.Address;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SellerResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(name = "email",unique = true)
    private String email;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last Name is mandatory")
    private  String lastName;
    private  boolean isDeleted;
    private  boolean isActive;
    private  boolean isExpired;
    private  boolean isLocked;
    @Size(min =15 , max = 15 , message = "Invalid GST number")
    @Column(unique = true)
    private  String gst;
    @Pattern(regexp="(^$|[0-9]{10})") //this matches either empty string or 10 digits number.
    private String companyContact;
    @Column(unique = true)
    private String  companyName;
    private Address address;

}

