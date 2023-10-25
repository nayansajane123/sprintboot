package com.app.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.demo.pojo.Users;

public interface AppUserDao extends JpaRepository<Users, Integer> 
{
	Users findByEmail(String email);
}

