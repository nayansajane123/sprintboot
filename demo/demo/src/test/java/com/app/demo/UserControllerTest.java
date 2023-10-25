package com.app.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.demo.dao.UserDao;
import com.app.demo.exception.AppException;
import com.app.demo.pojo.Users;
import com.app.demo.service.IUserService;

@SpringBootTest
public class UserControllerTest 
{
    @Autowired
    private IUserService iUserService;
    
    @MockBean
    private UserDao userDao;
    
    @Test
    public void getUsersTest()
    {
        when(userDao.findAll()).thenReturn(Stream.of(new Users(1, "Shital", "shital@gmail.com", "Shital#123", "ADMIN"), 
               new Users(2, "Amol", "amol@gmail.com", "Amol#123", "ADMIN")).collect(Collectors.toList()));
        
        assertEquals(2, iUserService.getAllUsers().size());
    }

    @Test
    public void registerUserTest() throws AppException
    {
        Users user = new Users(3, "Mudra", "mudra@gmail.com", "Mudra#123", "USER");
        when(userDao.save(user)).thenReturn(user);
        assertEquals(user, iUserService.registerUser(user));
    }
}
