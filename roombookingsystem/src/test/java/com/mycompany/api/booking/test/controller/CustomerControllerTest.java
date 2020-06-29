package com.mycompany.api.booking.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.api.booking.controller.CustomerController;
import com.mycompany.api.booking.entity.Customer;
import com.mycompany.api.booking.service.CustomerService;

@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	// test case for create customer
	@Test
	public void testCreateCustomer() throws Exception {

		Customer customer = new Customer(1L, "name1", "name2", new Date(), "name12@gamil.com", "1234567890");

		String inputJson = this.mapToJson(customer);
		String URI = "/api/customer";
		when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	// test case for get all customers

	@Test
	public void testGetAllCustomers() throws Exception {
		Customer customer1 = new Customer(1L, "name1", "name2", null, "name12@gamil.com", "1234567890");
		Customer customer2 = new Customer(2L, "abc", "xyz", null, "abc@gamil.com", "1234589890");

		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		when(customerService.getAllCustomers()).thenReturn(customerList);
		String URI = "/api/customer";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(customerList);
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedJson);
	}

	// test case for get customer by id

	@Test
	public void testGetAllCustomersById() throws Exception {
		Customer customer = new Customer(1L, "name1", "name2", null, "name12@gamil.com", "1234567890");

		when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(Optional.of(customer));
		String URI = "/api/customer/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(customer);
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedJson);
	}

	// test case for get customer by username

	@Test
	public void testGetAllCustomersByUserName() throws Exception {
		Customer customer = new Customer(1L, "name1", "name2", null, "name12@gamil.com", "1234567890");

		when(customerService.getCustomerByUsername(Mockito.anyString())).thenReturn(customer);
		String URI = "/api/customer/byusername/name12@gamil.com";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(customer);
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedJson);
	}

	/**
	 * Maps an Object into a JSON String using a Jackson ObjectMapper.
	 */
	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

}
