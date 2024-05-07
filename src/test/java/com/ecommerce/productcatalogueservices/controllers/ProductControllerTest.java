package com.ecommerce.productcatalogueservices.controllers;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    void testGetProductWithValidIDReturnSuccessfulProduct() {
        //Arrange - create obj and mock
        Product product = new Product();
        product.setId(1l);
        product.setName("T Shirt");
        product.setPrice(500.00);

        when(productService.getProduct(any(Long.class))).thenReturn(product);

        //Act - call method
        ResponseEntity<Product> responseEntity = productController.getProduct(1l);

        //Assert - check result
        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getBody().getName(), "T Shirt");
        assertEquals(responseEntity.getBody().getPrice(), 500.00);

    }

    @Test
    public void testGetProductExternalDependencyThrowException(){
        //Arrange
        when(productService.getProduct(any(Long.class))).thenThrow(new RuntimeException("external dependecy thrown exp."));
        //Act
        //Assert
        assertThrows(RuntimeException.class, () -> productController.getProduct(1l));
    }

    @Test
    @DisplayName("test getProduct with invalid input then throw exception")
    public void testGetProductWithInvalidIdThrowException(){
        //Arrange
        //Act
        //Assert
        assertThrows(IllegalArgumentException.class, () -> productController.getProduct(0l));
        verify(productService,times(0)).getProduct(0l);
    }
}