package com.example.bootcamp.repos;

import com.example.bootcamp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
