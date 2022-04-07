//package com.example.bootcamp.entities;
//
//import javax.persistence.*;
//
//@Entity
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "customer_user_id")
//    @MapsId
//    private Customer customer;
//
//    private Integer quantity;
//
//    private Boolean isWishlistItem;
//
//    @OneToOne
//    @JoinColumn(name = "product_variation_id")
//    private ProductVariation productVariation;
//}
