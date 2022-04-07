//package com.example.bootcamp.entities;
//
//import javax.persistence.*;
//
//public class ProductReview {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String review;
//    private Integer rating;
//
//    @OneToOne
//    @MapsId
//    private Customer customer;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//}
