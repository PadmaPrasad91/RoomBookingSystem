package com.mycompany.api.booking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer_detail")
public class Customer {

	/**
	 * This is Entity class
	 */

	// This is unique id for customer

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// This field is first name of customer

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstname;

	// This field is last name of customer

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastname;

	// This field is date of birth of customer

	@Column(name = "dob")
	private Date dob;

	// This field is user name of a customer and customers email id

	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String username;

	// This field is password of customer

	@Size(min = 8, max = 10, message = "password should contain min 8 and max 10 characters")
	@Column(name = "password", length = 10, nullable = false)
	private String password;

	// Default constructor for entity Customer

	public Customer() {
		super();
	}

	// Constructor with fields for entity Customer

	public Customer(Long id, String firstname, String lastname, Date dob, String username, String password) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.username = username;
		this.password = password;
	}

	// Getters and Setters for entity Customer

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString method for debugging
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob
				+ ", username=" + username + ", password=" + password + "]";
	}
}
