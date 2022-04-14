package com.example.bootcamp.repos;

import com.example.bootcamp.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
         ConfirmationToken findByConfirmationToken(String confirmationToken);

         ConfirmationToken findByUserId(Long id);
    }
