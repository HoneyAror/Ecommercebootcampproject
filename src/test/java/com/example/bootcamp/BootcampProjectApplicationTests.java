package com.example.bootcamp;

import com.example.bootcamp.entities.Customer;
import com.example.bootcamp.entities.Seller;
import com.example.bootcamp.entities.User;
import com.example.bootcamp.repos.CustomerRepository;
import com.example.bootcamp.repos.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootcampProjectApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public  void getCustomer(){
		Customer customer=customerRepository.findById(10L).get();
		User user=userRepository.findByEmail(customer.getUser().getEmail());
		System.out.println(user);

	}


}
