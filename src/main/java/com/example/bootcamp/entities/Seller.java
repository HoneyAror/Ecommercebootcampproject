package com.example.bootcamp.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Seller extends Auditinginfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Size(min =15 , max = 15 , message = "Invalid GST number")
    private  String gst;
    private String companyContact;
    private String  companyName;

    @OneToOne
    @MapsId//maps primary key of both table
    private User user;

    public Seller(Long userId, String gst, String companyContact, String companyName) {
        this.userId = userId;
        this.gst = gst;
        this.companyContact = companyContact;
        this.companyName = companyName;
    }
}
