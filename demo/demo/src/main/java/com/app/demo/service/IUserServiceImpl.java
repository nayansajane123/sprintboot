package com.app.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.demo.dao.UserDao;
import com.app.demo.dto.UserDTO;
import com.app.demo.exception.AppException;
import com.app.demo.pojo.Users;

@Service
@Transactional
public class IUserServiceImpl implements IUserService
{
	@Autowired
	UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
	 private UserDao userRepository;
	
	@Override
	public Users registerUser(Users user) throws AppException 
	{
		String email = user.getEmail();
		Users isExist = userDao.findByEmail(email);
		System.out.println(user);
		if(isExist == null)
		{
			return userDao.save(user);
		}
		else
		{
			throw new AppException("Email already Exist");
		}
	}

	@Override
	public UserDTO authenticateUser(String email, String password) throws AppException 
	{
		System.out.println("In service password --------------------------------" + password);
		Users isAvailable = null;
		UserDTO userDTO = null;
		if(email !=null && password !=null)
			isAvailable = userDao.findByEmail(email);
		else
			throw new AppException("Credentials can't be empty");
		
		String encodedPassword = isAvailable.getPassword();
		Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
		if(isAvailable != null)
		{
			if(isPwdRight)
			{
				userDTO = new UserDTO();
				BeanUtils.copyProperties(isAvailable, userDTO, "password");
			}
			else
				throw new AppException("Invalid Details");
		}
		else
			throw new AppException("No such user exist");
		return userDTO;
	}

	@Override
	public List<Users> getAllUsers() 
	{
		List<Users> userList = new ArrayList<>();
		userDao.findAll().forEach(u -> {
		Users user = new Users();
		BeanUtils.copyProperties(u, user);
		userList.add(user);
		});
		return userList;
	}


}
