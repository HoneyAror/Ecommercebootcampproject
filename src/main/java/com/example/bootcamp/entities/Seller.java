package com.example.bootcamp.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Seller extends Auditinginfo{
    @Id
    private Long sellerid;
    private  String gst;
    private int companyContact;
    private String  companyName;

    @OneToOne
    @MapsId  //maps primary key of both table
    private User user;

}
