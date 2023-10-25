package com.app.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.app.demo.pojo.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Integer> 
{
	Users findByEmail(String email);
	Users findByEmailAndPassword(String email, String password);
}
