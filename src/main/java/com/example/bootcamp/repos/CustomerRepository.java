package com.example.bootcamp.repos;

import com.example.bootcamp.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
     @Query("from Customer")
     List<Customer> findAllCustomer(Pageable pageable);

}
