//package com.example.bootcamp.entities;
//
//import javax.persistence.*;
//
//@Entity
//public class ProductVariation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productVariation")
//    private OrderProduct orderProduct;
//
//    private Integer quantityAvailable;
//    private Integer price;
//    private String primaryImageName;
//    private Boolean isActive;
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productVariation")
//    private Cart cart;
//}
