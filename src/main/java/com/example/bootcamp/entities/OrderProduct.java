//package com.example.bootcamp.entities;
//
//import javax.persistence.*;
//
//@Entity
//public class OrderProduct {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "orders_id")
//    private Orderinfo orders;
//
//    @OneToOne
//    @JoinColumn(name = "product_variation_id")
//    private ProductVariation productVariation;
//
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderProduct")
//    private OrderStatus orderStatus;
//
//    private Integer quantity;
//    private Double price;
//}
