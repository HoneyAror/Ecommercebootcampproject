package com.example.bootcamp.dto;   //data transfer object

import com.example.bootcamp.entities.Address;
import com.example.bootcamp.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {
    @Email
    @Column(name = "email",unique = true)
    private String email;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last Name is mandatory")
    private  String lastName;
    @Pattern(regexp = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%*!^&+=])" + "(?=\\S+$).{8,15}$",
            message = "Password must contain 8-15 Characters with atleast 1 Lower case, 1 Upper case, 1 Special Character, 1 Number")
    @NotBlank(message = "Password field is mandatory")
    private  String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmpassword;
    private  boolean isDeleted;
    private  boolean isActive;
    private  boolean isExpired;
    private  boolean isLocked;
    private  Integer invalidAttemptCount;
    @Temporal(TemporalType.DATE)
    private Date passwordUpdatedDate;
    private Set<Role> roles;
    private AddressDTO address;

    public UserDTO() {
    }

    public UserDTO(String email, String firstName, String middleName, String lastName, String password, String confirmpassword, boolean isActive, boolean isDeleted, boolean isExpired, boolean isLocked, Integer invalidAttemptCount){
        this.email=email;
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.password=password;
        this.confirmpassword=confirmpassword;
        this.isActive=isActive;
        this.isDeleted=isDeleted;
        this.isExpired=isExpired;
        this.isLocked=isLocked;
        this.invalidAttemptCount=invalidAttemptCount;
    }

}
