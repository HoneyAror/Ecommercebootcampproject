package com.example.bootcamp.repos;

import com.example.bootcamp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByAuthority(String authority);
}
