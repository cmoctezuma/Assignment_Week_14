package com.promineotech.inventoryManagement.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.inventoryManagement.entity.Customer;
import com.promineotech.inventoryManagement.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	private CustomerService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCustomer(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getCustomerById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getCustomers() {
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, this is a warning!");
		logger.error("Oops!We have an error");
		logger.fatal("Something really went wrong!");
		return new ResponseEntity<Object>(service.getCustomers(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<Object>(service.createCustomer(customer), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateCustomer(customer, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
		try {
			service.deleteCustomer(id);
			return new ResponseEntity<Object>("Successfully deleted customer with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
