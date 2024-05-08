package com.ecommerce.productcatalogueservices.controllers;

import com.ecommerce.productcatalogueservices.dtos.ProductDTO;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import com.ecommerce.productcatalogueservices.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        Product product1 = new Product();
        product1.setName("T Shirt");
        product1.setPrice(500.00);
        products.add(product1);

        Product product2 = new Product();
        product2.setName("IPhone 14");
        product2.setId(1l);
        products.add(product2);

        Product product3 = new Product();
        product3.setName("IPhone 15");
        product3.setId(2l);
        products.add(product3);

        when(productService.getAllProducts()).thenReturn(products);

        //Act
        //Assert
        mockMvc.perform(get("/products")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)))
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].name").value("T Shirt"))
                .andExpect(jsonPath("$[1].id").value(1));
    }

    @Test
    public void testCreateProductSuccefully() throws Exception{
        //Arrange
        Product expectedProduct = new Product();
        expectedProduct.setName("IPhone 14");
        expectedProduct.setId(1l);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1l);
        productDTO.setName("IPhone 14");

        when(productService.createProduct(any(Product.class))).thenReturn(expectedProduct);

        //Act
        //Assert

        //post and check result
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))
                .andExpect(jsonPath("$.length()").value(2)) //output json check
                .andExpect(jsonPath("$.name").value("IPhone 14"));



    }

}
