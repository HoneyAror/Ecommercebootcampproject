package com.example.bootcamp.dto;

import com.example.bootcamp.entities.Address;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CustomerResponseDTO {

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
    private Long contact;
    private Address address;
}
