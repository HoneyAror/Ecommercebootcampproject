package com.example.bootcamp.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class Address extends Auditinginfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=3,message = "City name must have atleast 3 characters")
    private String city;
    @Size(min=3,message = "State name must have atleast 3 characters")
    private  String state;
    @Size(min=3,message = "Country name must have atleast 3 characters")
    private String  country;
    private String addressLine;
    private Integer zipCode;
    private String label;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

}
