package com.example.bootcamp.dto;

import com.example.bootcamp.entities.Address;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class SellerDTO extends UserDTO {
    @Size(min =15 , max = 15 , message = "Invalid GST number")
    @Column(unique = true)
    private  String gst;
    @Pattern(regexp="(^$|[0-9]{10})") //this matches either empty string or 10 digits number.
    private String companyContact;
    @Column(unique = true)
    private String  companyName;
//    private Address address;

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public SellerDTO(String email, String firstName, String middleName, String lastName, String password, String confirmpassword,boolean isActive, boolean isDeleted, boolean isExpired, boolean isLocked, int invalidAttemptCount, String gst, String companyContact, String companyName){
        super(email,firstName,middleName,lastName,password,confirmpassword,isActive,isDeleted,isExpired,isLocked,invalidAttemptCount);
        this.gst=gst;
        this.companyContact=companyContact;
        this.companyName=companyName;
//        this.address=address;
  }


}
