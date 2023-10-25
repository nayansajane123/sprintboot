package com.app.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.demo.pojo.Product;

public interface ProductDao  extends JpaRepository<Product,Integer>
{

}
