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

import com.promineotech.inventoryManagement.entity.Product;
import com.promineotech.inventoryManagement.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	private ProductService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getProducts() {

		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, this is a warning!");
		logger.error("Oops!We have an error");
		logger.fatal("Something really went wrong!");

		return new ResponseEntity<Object>(service.getProducts(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		return new ResponseEntity<Object>(service.createProduct(product), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateProduct(product, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update product: ", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
		try {
			service.removeProduct(id);
			return new ResponseEntity<Object>("Successfully deleted this product with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete product.", HttpStatus.BAD_REQUEST);
		}
	}

}
