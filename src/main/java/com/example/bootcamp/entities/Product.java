//package com.example.bootcamp.entities;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "seller_user_id")
//    private Seller seller;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//    private Set<ProductVariation> productVariation;
//
//    private String name;
//    private String description;
//
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;
//    private Boolean isCancellable;
//    private Boolean isReturnable;
//    private String brand;
//    private Boolean isActive;
//    private Boolean isDeleted;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//    private Set<ProductReview> productReview;
//
//}
