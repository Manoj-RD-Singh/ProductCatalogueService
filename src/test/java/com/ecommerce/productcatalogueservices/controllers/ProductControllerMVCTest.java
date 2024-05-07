package com.ecommerce.productcatalogueservices.controllers;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import com.ecommerce.productcatalogueservices.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
public class ProductControllerMVCTest {

    @Autowired
    private ProductController productController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private IProductService productService;

    @Test
    public void  testGetAllProductsRReturnEmptyProduct() throws Exception{
        //Arrange
        //Act
        mockMvc.perform(get("/products")).andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void testGetAllProductsReturnProducts() throws Exception{
        //Arrange
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setName("T Shirt");
        product.setPrice(500.00);
        products.add(product);

        when(productService.getAllProducts()).thenReturn(products);

        //Act
        //Assert
        mockMvc.perform(get("/products")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));


    }

}
