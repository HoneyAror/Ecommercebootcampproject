//package com.example.bootcamp.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.util.Map;
//
//@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//public class ProductVariation {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private int quantityAvailable;
//    private float price;
//    private boolean isActive;
//    private String primaryImageName;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public int getQuantityAvailable() {
//        return quantityAvailable;
//    }
//
//    public void setQuantityAvailable(int quantityAvailable) {
//        this.quantityAvailable = quantityAvailable;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }
//
//    public boolean isActive() {
//        return isActive;
//    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }
//
//
//    public String getPrimaryImageName() {
//        return primaryImageName;
//    }
//
//    public void setPrimaryImageName(String primaryImageName) {
//        this.primaryImageName = primaryImageName;
//    }
//}