package com.app.demo.service;

import java.util.List;

import com.app.demo.dto.UserDTO;
import com.app.demo.exception.AppException;
import com.app.demo.pojo.Users;

public interface IUserService 
{
	Users registerUser(Users user) throws AppException;
	UserDTO authenticateUser(String email, String password) throws AppException;
	List<Users> getAllUsers();
}
