package com.example.bootcamp.repos;

import com.example.bootcamp.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
}
