package com.example.bootcamp.dto;

import javax.validation.constraints.Pattern;

public class CustomerDTO extends UserDTO {
    //@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",
           // message = "Phone number not vaild")
    //@Pattern(regexp="(^$|[0-9]{10})",message = "Phone number not valid")
    private Long contact;
    //private Set<Address> addresses;

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }
//
//    public Set<Address> getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(Set<Address> addresses) {
//        this.addresses = addresses;
//    }

    public CustomerDTO(String email, String firstName, String middleName, String lastName, String password, String confirmpassword,boolean isActive, boolean isDeleted, boolean isExpired, boolean isLocked, int invalidAttemptCount, Long contact){
        super(email, firstName, middleName, lastName, password,confirmpassword, isActive, isDeleted, isExpired, isLocked, invalidAttemptCount);
        this.contact=contact;
        //this.addresses=addresses;
    }


}
