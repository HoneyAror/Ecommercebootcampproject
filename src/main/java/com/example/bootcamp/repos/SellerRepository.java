package com.example.bootcamp.repos;

import com.example.bootcamp.entities.Customer;
import com.example.bootcamp.entities.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller,Long> {

    @Query("from Seller")
    List<Seller> findAllSeller(Pageable pageable);
}
