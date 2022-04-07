//package com.example.bootcamp.entities;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
//    private Set<Product> product;
//
//    @ManyToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "parent_category_id")
//    private Category parent;
//
//    @OneToMany(mappedBy = "parent")
//    private Set<Category> subCategory;
//}
