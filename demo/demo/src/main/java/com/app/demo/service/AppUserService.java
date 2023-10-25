package com.app.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.demo.dao.AppUserDao;
import com.app.demo.pojo.Users;



@Service
public class AppUserService implements UserDetailsService 
{
	
	@Autowired
	private AppUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
	{
		Users appUser = userDao.findByEmail(email);
		System.out.println("Inside secuirty AppUserService method -------------------------------" + appUser);
		return appUser.toUser();
	}
}

