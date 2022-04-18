package com.example.bootcamp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {
    }

    public Address(String city, String state, String country, String addressLine, Integer zipCode, String label){
        this.city=city;
        this.state=state;
        this.country=country;
        this.addressLine=addressLine;
        this.zipCode=zipCode;
        this.label=label;
    }

}
