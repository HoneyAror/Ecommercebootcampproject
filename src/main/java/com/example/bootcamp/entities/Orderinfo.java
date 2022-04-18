//package com.example.bootcamp.entities;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//public class Orderinfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_user_id")
//    private Customer customer;
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orders")
//    private OrderProduct orderProduct;
//
//    private Double amountPaid;
//
//    @Temporal(TemporalType.DATE)
//    private Date dateCreated;
//
//    private String paymentMethod;
//    private String customerAddressCity;
//    private String customerAddressState;
//    private String customerAddressCountry;
//    private String customerAddressAddressLine;
//    private String customerAddressZipCode;
//    private String customerAddressLabel;
//}
