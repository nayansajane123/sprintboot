package com.app.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.demo.dao.ProductDao;
import com.app.demo.exception.AppException;
import com.app.demo.pojo.Product;


@Service
@Transactional
public class IProductServiceImpl implements IProductService
{
	@Autowired
	ProductDao productDao;
	
	@Override
	public Optional<Product> findById(int id)
	{
		return productDao.findById(id);
	}
	
	@Override
	public List<Product> getAllProducts() 
	{
		List<Product> productList = new ArrayList<>();
		productDao.findAll().forEach(p -> {
		Product product = new Product();
		BeanUtils.copyProperties(p, product);
		productList.add(product);
		});
		return productList;
	}

	@Override
	public String deleteProductById(int id) throws AppException 
	{
		Product product = productDao.findById(id).orElseThrow(() -> new AppException("Invalid Customer Id"));
		productDao.deleteById(id);
		return "Product with id " +id + "deleted successsfully!!!";
	}

	@Override
	public Product updateProductById(int id, Product newProduct) throws AppException 
	{
		Product Poduct = productDao.findById(id).get();
		BeanUtils.copyProperties(newProduct, Poduct);
		return newProduct;
	}

	@Override
	public Product saveProduct(Product newProduct) throws AppException 
	{
		Product saveProduct = productDao.save(newProduct);
		return saveProduct;
	}

}
