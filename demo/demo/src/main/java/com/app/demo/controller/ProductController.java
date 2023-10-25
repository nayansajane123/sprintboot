package com.app.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.demo.exception.AppException;
import com.app.demo.pojo.Product;
import com.app.demo.service.IProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController 
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	IProductService iProductService;
	
	@GetMapping("/AllProducts")
	public ResponseEntity<?> getAllProduct()
	{
		logger.info("Request received for fetching all product details");
		List<Product> productList = null;
		productList = iProductService.getAllProducts();
		
		logger.info("Result:"+productList);
		
		if(productList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) throws AppException
	{
		logger.info("In delete method :" + id);
		try {
			return new ResponseEntity<>(iProductService.deleteProductById(id), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product details) throws AppException
	{
		details.setId(id);
		
		try {
			return new ResponseEntity<>(iProductService.updateProductById(id, details), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/AddProd")
	public ResponseEntity<?> addProduct(@RequestBody Product details) throws AppException
	{
		logger.info("Product Details : " + details);
		Product prod = iProductService.saveProduct(details);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
