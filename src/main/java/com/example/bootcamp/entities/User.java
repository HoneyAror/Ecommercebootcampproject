package com.example.bootcamp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Data
public class User extends Auditinginfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(name = "email",unique = true)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    private  String lastName;
    private  String password;
    @Transient
    private String confirmpassword;
    private  boolean isDeleted;
    private  boolean isActive;
    private  boolean isExpired;
    private  boolean isLocked;
    private  Integer invalidAttemptCount;
    @Temporal(TemporalType.DATE)
    private Date passwordUpdatedDate;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> roles;

    @JsonManagedReference
    @OneToMany
    private Set<Address> addresses=new HashSet<>();

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Seller seller;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Customer customer;


//    public  void addAddress(Address address) {
//        if (address != null) {
//            if (addresses == null) {
//                addresses = new HashSet<>();
//            }
//            addresses.add(address);
//        }
//    }


    @Transient
    private List<GrantedAuthority> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", passwordUpdatedDate=" + passwordUpdatedDate +
                '}';
    }
}
