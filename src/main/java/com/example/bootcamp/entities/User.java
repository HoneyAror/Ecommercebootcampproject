package com.example.bootcamp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Data
public class User extends Auditinginfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(name = "email",unique = true)
    private String email;
    @NotNull
    private String firstName;
    private String middleName;
    private  String lastName;
    @Size(min = 8,max = 15,message = "Password must contain 8-15 Characters with atleast 1 Lower case, 1 Upper case, 1 Special Character, 1 Number")
    //@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,15}$")
    private  String password;
    private  boolean isDeleted;
    private  boolean isActive;
    private  boolean isExpired;
    private  boolean isLocked;
    private  Integer invalidAttemptCount;
    @Temporal(TemporalType.DATE)
    private Date passwordUpdatedDate;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> roles;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Address> addresses;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Seller seller;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Customer customer;


    public  void addAddress(Address address) {
        if (address != null) {
            if (addresses == null) {
                addresses = new HashSet<>();
            }
            addresses.add(address);
        }
    }

}
