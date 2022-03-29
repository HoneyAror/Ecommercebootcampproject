package com.example.bootcamp.repos;

import com.example.bootcamp.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
