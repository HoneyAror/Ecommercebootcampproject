package com.example.bootcamp.entities;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
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
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private  String password;
    private  boolean isDeleted;
    private  boolean isActive;
    private  boolean isExpired;
    private  boolean isLocked;
    private  Integer invalidAttemptCount;
    @Temporal(TemporalType.DATE)
    private Date passwordUpdatedDate;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Seller seller;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Integer getInvalidAttemptCount() {
        return invalidAttemptCount;
    }

    public void setInvalidAttemptCount(Integer invalidAttemptCount) {
        this.invalidAttemptCount = invalidAttemptCount;
    }

    public Date getPasswordUpdatedDate() {
        return passwordUpdatedDate;
    }

    public void setPasswordUpdatedDate(Date passwordUpdatedDate) {
        this.passwordUpdatedDate = passwordUpdatedDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public  void addAddress(Address address) {
        if (address != null) {
            if (addresses == null) {
                addresses = new LinkedList<>();
            }
            addresses.add(address);
        }
    }

}
