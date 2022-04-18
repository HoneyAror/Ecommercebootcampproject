package com.example.bootcamp.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class AddressDTO {
    @Size(min=3,message = "City name must have atleast 3 characters")
    private String city;
    @Size(min=3,message = "State name must have atleast 3 characters")
    private  String state;
    @Size(min=3,message = "Country name must have atleast 3 characters")
    private String  country;
    private String addressLine;
    private Integer zipCode;
    private String label;

    public AddressDTO() {
    }

    public AddressDTO(String city, String state, String country, String addressLine, Integer zipCode, String label){
        this.city=city;
        this.state=state;
        this.country=country;
        this.addressLine=addressLine;
        this.zipCode=zipCode;
        this.label=label;
    }

}
