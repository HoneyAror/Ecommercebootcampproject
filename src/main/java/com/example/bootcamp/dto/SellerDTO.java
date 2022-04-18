package com.example.bootcamp.dto;

import com.example.bootcamp.entities.Address;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;

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


    public SellerDTO(String email, String firstName, String middleName, String lastName, String password, String confirmpassword,boolean isActive, boolean isDeleted, boolean isExpired, boolean isLocked, int invalidAttemptCount, String gst, String companyContact, String companyName){
        super(email,firstName,middleName,lastName,password,confirmpassword,isActive,isDeleted,isExpired,isLocked,invalidAttemptCount);
        this.gst=gst;
        this.companyContact=companyContact;
        this.companyName=companyName;
  }

    public SellerDTO() {
    }
//
//    public SellerDTO(String email, String firstName, String middleName, String lastName, String password, String confirmpassword, boolean isActive, boolean isDeleted, boolean isExpired, boolean isLocked, Integer invalidAttemptCount, String gst, String companyContact, String companyName, Address address) {
//        super(email, firstName, middleName, lastName, password, confirmpassword, isActive, isDeleted, isExpired, isLocked, invalidAttemptCount);
//        this.gst = gst;
//        this.companyContact = companyContact;
//        this.companyName = companyName;
//        this.address = address;
//    }

//    public static Seller mapper(SellerDTO sellerTO,User user)
//    {
//        Seller seller = new Seller();
//        seller.setCompanyName(sellerTO.getCompanyName());
//        seller.setCompanyContact(sellerTO.getCompanyContact());
//        seller.setGst(sellerTO.getGst());
//        Address address= sellerTO.getAddress();
//        Address sellerAddress = new Address();
//        sellerAddress.setCity(address.getCity());
//        sellerAddress.setState(address.getState());
//        sellerAddress.setCountry(address.getCountry());
//        sellerAddress.setAddressLine(address.getAddressLine());
//        sellerAddress.setLabel(address.getLabel());
//        sellerAddress.setZipCode(address.getZipCode());
//        sellerAddress.setUser(user);
//        seller.setUser(user);
//        return seller;
//    }

}
