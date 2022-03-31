package com.example.bootcamp.dto;   //data transfer object

import com.example.bootcamp.entities.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {
    @Email
    @Column(name = "email",unique = true)
    private String email;
    @NotNull
    private String firstName;
    private String middleName;
    private  String lastName;
    @Size(min = 8,max = 15,message = "Password must contain 8-15 Characters with atleast 1 Lower case, 1 Upper case, 1 Special Character, 1 Number")
    //@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private  String password;
    private  boolean isDeleted;
    private  boolean isActive;
    private  boolean isExpired;
    private  boolean isLocked;
    private  Integer invalidAttemptCount;
    @Temporal(TemporalType.DATE)
    private Date passwordUpdatedDate;
    private Set<Role> roles;

    public UserDTO(String email, String firstName, String middleName, String lastName, String password, boolean isActive, boolean isDeleted, boolean isExpired, boolean isLocked, Integer invalidAttemptCount){
        this.email=email;
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.password=password;
        this.isActive=isActive;
        this.isDeleted=isDeleted;
        this.isExpired=isExpired;
        this.isLocked=isLocked;
        this.invalidAttemptCount=invalidAttemptCount;
    }

}
