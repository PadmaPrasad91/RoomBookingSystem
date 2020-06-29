package com.mycompany.api.booking.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mycompany.api.booking.entity.Customer;
import com.mycompany.api.booking.exception.CustomerExistsException;
import com.mycompany.api.booking.exception.CustomerNotFoundException;
import com.mycompany.api.booking.repository.CustomerRepository;
import com.mycompany.api.booking.service.CustomerService;

@SpringBootTest
public class CustomerServiceTestCase {

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepository;

	/**
	 * Get all customers
	 */
	@Test
	public void testGetAllCustomers() {
		when(customerRepository.findAll()).thenReturn(Stream
				.of(new Customer(1L, "name1", "name2", new Date(), "name12@gamil.com", "1234567890"),
						new Customer(2L, "name3", "name4", new Date(), "name34@gamil.com", "1234567890"))
				.collect(Collectors.toList()));
		assertEquals(2, customerService.getAllCustomers().size());
	}

	/**
	 * Add/Create Customer
	 * 
	 * @throws CustomerExistsException
	 */

	@Test
	public void testCreateCustomer() throws CustomerExistsException {
		Customer customer = new Customer(1L, "name1", "name2", new Date(), "name12@gamil.com", "1234567890");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.createCustomer(customer));
	}

	/**
	 * Get Customer based on id
	 * 
	 * @throws CustomerNotFoundException
	 */

	@Test
	public void testGetCustomerById() throws CustomerNotFoundException {
		Customer customer = new Customer(1L, "name1", "name2", new Date(), "name12@gamil.com", "1234567890");
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		assertEquals(Optional.of(customer), customerService.getCustomerById(1L));
	}

	/**
	 * Update Customer by id
	 * 
	 * @throws CustomerNotFoundException
	 */

	@Test
	public void testUpdateCustomerById() throws CustomerNotFoundException {
		Customer customer = new Customer(1L, "name1", "name2", new Date(), "name12@gamil.com", "1234567890");
		when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
		customer.setUsername("abc@xyz.com");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.updateCustomerById(1L, customer));
	}

	/**
	 * Delete Customer by id
	 * 
	 * @throws CustomerNotFoundException
	 */

	@Test
	public void testDeleteCustomerById() throws CustomerNotFoundException {
		Customer customer = new Customer(2L, "name1", "name2", new Date(), "name12@gamil.com", "1234567890");
		customerService.deleteCustomer(customer.getId());
		verify(customerRepository, times(1)).deleteById(customer.getId());

	}

	/**
	 * Get Customer by username
	 */

	@Test
	public void testGetCustomerByUsername() throws CustomerNotFoundException {
		Customer customer = new Customer(1L, "name1", "name2", new Date(), "name12@gamil.com", "1234567890");
		when(customerRepository.getCustomerByUsername(customer.getUsername())).thenReturn(customer);
		assertEquals(customer, customerService.getCustomerByUsername("name12@gamil.com"));
	}

}
