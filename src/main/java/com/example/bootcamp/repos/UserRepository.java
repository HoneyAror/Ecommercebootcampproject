package com.example.bootcamp.repos;

import com.example.bootcamp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

//    @Query(value = "select * from user inner join customer on user.id=customer.user_id ",nativeQuery = true)
//    List<User> findAllCustomer();

}
