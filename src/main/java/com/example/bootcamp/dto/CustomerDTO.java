package com.example.bootcamp.dto;

public class CustomerDTO extends UserDTO {
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

    public CustomerDTO(String email, String firstName, String middleName, String lastName, String password, boolean isActive, boolean isDeleted, boolean isExpired, boolean isLocked, int invalidAttemptCount, Long contact){
        super(email, firstName, middleName, lastName, password, isActive, isDeleted, isExpired, isLocked, invalidAttemptCount);
        this.contact=contact;
        //this.addresses=addresses;
    }


}
