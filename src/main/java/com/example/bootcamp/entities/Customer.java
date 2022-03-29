package com.example.bootcamp.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class Customer extends Auditinginfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private Long contact;

    @OneToOne
    @MapsId
    private User user;

}
