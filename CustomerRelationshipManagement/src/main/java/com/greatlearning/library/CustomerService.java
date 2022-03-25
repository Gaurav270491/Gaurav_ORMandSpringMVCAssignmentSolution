package com.greatlearning.library;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
	public List<Customer> findAll();

	public Customer findById(int theId);

	public void save(Customer theCustomer);

	public void deleteById(int theId);

	
}
