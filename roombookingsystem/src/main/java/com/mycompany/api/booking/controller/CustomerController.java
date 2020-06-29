package com.mycompany.api.booking.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mycompany.api.booking.entity.Customer;
import com.mycompany.api.booking.exception.CustomerExistsException;
import com.mycompany.api.booking.exception.CustomerNotFoundException;
import com.mycompany.api.booking.exception.UserNameNotFoundException;
import com.mycompany.api.booking.service.CustomerService;

@Validated
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	// Autowire the CustomerService
	@Autowired
	private CustomerService customerService;

	// fetch all customers
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	// Create Customer
	// @RequestBody Annotation
	// @PostMapping annotation
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		try {
			Customer newCustomer = customerService.createCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
		} catch (CustomerExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// fetch customer by id
	@GetMapping("/{id}")
	public Optional<Customer> getCustomerById(@PathVariable Long id) {
		try {
			return customerService.getCustomerById(id);
		} catch (CustomerNotFoundException e) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	// update customer by id
	@PutMapping("/{id}")
	public Customer updateCustomerById(@PathVariable Long id, @RequestBody Customer customer) {
		try {
			return customerService.updateCustomerById(id, customer);
		} catch (CustomerNotFoundException e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// delete customer by id
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		try {
			customerService.deleteCustomer(id);
			ResponseEntity.status(HttpStatus.OK);
		} catch (CustomerNotFoundException e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// get customer by username
	@GetMapping("/byusername/{username}")
	public Customer getCustomerByUserName(@PathVariable String username) throws UserNameNotFoundException {
		Customer customer = customerService.getCustomerByUsername(username);
		if (customer == null) {
			throw new UserNameNotFoundException("Username: '" + username + "' not found in our database!");
		}
		return customer;
	}
}
