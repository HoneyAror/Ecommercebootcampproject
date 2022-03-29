package com.example.bootcamp;

import com.example.bootcamp.entities.Customer;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.repos.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootcampProjectApplicationTests {
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void contextLoads() {
	}



}
