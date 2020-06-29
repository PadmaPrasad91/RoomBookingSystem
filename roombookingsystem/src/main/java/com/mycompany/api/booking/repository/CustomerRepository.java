package com.mycompany.api.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.api.booking.entity.Customer;

/**
 * Customer Repository
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer getCustomerByUsername(String username);
}
