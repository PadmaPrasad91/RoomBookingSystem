package com.mycompany.api.booking.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mycompany.api.booking.entity.Customer;
import com.mycompany.api.booking.exception.CustomerExistsException;
import com.mycompany.api.booking.exception.CustomerNotFoundException;
import com.mycompany.api.booking.repository.CustomerRepository;

/**
 * Customer Service
 */

@Service
public class CustomerService {

	// Autowire the UserRepository
	@Autowired
	private CustomerRepository customerRepository;

	// This method is to fetch all customers
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	// This method is to Create Customer
	public Customer createCustomer(Customer customer) throws CustomerExistsException {

		Customer existingCustomer = customerRepository.getCustomerByUsername(customer.getUsername());
		if (existingCustomer != null) {
			throw new CustomerExistsException("Username already exists in our database!");
		}

		return customerRepository.save(customer);
	}

	// This method is to fetch the customer by customer id
	public Optional<Customer> getCustomerById(Long id) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found in our database.Please provide correct id.");
		}
		return customer;
	}

	// This method is to update the customer details by id
	public Customer updateCustomerById(Long id, Customer customer) throws CustomerNotFoundException {
		Optional<Customer> updateCustomer = customerRepository.findById(id);
		if (!updateCustomer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found in our database.Please provide correct id.");
		}
		return customerRepository.save(customer);
	}

	// This method is to delete the customer by id
	public void deleteCustomer(Long id) throws CustomerNotFoundException {
		Optional<Customer> deleteCustomer = customerRepository.findById(id);

		if (deleteCustomer == null) {
			throw new CustomerNotFoundException("Customer not found in our database.Please provide correct id.");

		}

		customerRepository.deleteById(id);
	}

	// This method is to fetch customer by username
	public Customer getCustomerByUsername(String username) {
		return customerRepository.getCustomerByUsername(username);
	}
}
