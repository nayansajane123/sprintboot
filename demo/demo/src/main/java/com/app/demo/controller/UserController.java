package com.app.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.demo.dto.UserDTO;
import com.app.demo.exception.AppException;
import com.app.demo.pojo.Users;
import com.app.demo.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController 
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	IUserService iUserService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<?> userRegistration(@RequestBody Users user) throws AppException
	{
		System.out.println(user);
		logger.info("user details"+ user);
		try {
			
			String password = this.passwordEncoder.encode(user.getPassword());
			user.setPassword(password);
			Users u = iUserService.registerUser(user);
			
			if(u != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
			{
				logger.error("Invalid Customer Registration Data!!");
				throw new AppException("Invalid Customer Registration Data!!");
			}
		}
		catch(RuntimeException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Users user) throws AppException
	{
		try
		{
			logger.info("user details"+ user);
			String email = user.getEmail();
			String password = user.getPassword();
			UserDTO userDTO = iUserService.authenticateUser(email, password);
			
			if(userDTO != null)
				return new ResponseEntity<>(userDTO, HttpStatus.OK);
			else
			{
				logger.error("Something went wrong!!");
				throw new AppException("Something went wrong");
			}
		}
		catch(RuntimeException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUser()
	{
		List<Users> userList = null;
		userList = iUserService.getAllUsers();
		
		if(userList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
}
