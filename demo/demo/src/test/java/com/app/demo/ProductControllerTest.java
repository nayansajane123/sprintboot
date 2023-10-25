package com.app.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension; 

import com.app.demo.dao.ProductDao;
import com.app.demo.pojo.Product;
import com.app.demo.service.IProductService;

import org.junit.jupiter.api.extension.ExtendWith; 

@ExtendWith(SpringExtension.class) 
@SpringBootTest
class ProductControllerTest 
{
    @Autowired
    private IProductService iProductService;

    @MockBean
    private ProductDao productDao;

    @Test 
    public void getProductListTest()
    {
        when(iProductService.getAllProducts()).thenReturn(Stream.of(new Product(1, "White", 25000, "Samsung", 4, 4, "F13", 128), 
               new Product(2, "White", 25000, "Samsung", 4, 4, "F13", 128)).collect(Collectors.toList()));
        
        assertEquals(2, iProductService.getAllProducts().size());
    }
}
