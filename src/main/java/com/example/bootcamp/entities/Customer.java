package com.example.bootcamp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Customer extends Auditinginfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private Long contact;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;

    public Customer(Long userId,Long contact){
        this.userId =userId;
        this.contact=contact;
    }


}
