package com.app.demo.service;

import java.util.List;
import java.util.Optional;

import com.app.demo.exception.AppException;
import com.app.demo.pojo.Product;


public interface IProductService 
{
	List<Product> getAllProducts();
	String deleteProductById(int id) throws AppException;
	Product updateProductById(int id, Product product ) throws AppException;
	Product saveProduct(Product product) throws AppException;
	
	Optional<Product> findById(int id);
}
