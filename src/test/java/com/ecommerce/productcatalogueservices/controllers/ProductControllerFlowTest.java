package com.ecommerce.productcatalogueservices.controllers;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {

    @Autowired
    ProductController productController;

    @Autowired
    @Qualifier("productServiceStub")
    IProductService productService;

    @Test
    public void testProductEndToEnd(){
        Product p = new Product();
        p.setId(1l);
        p.setName("Nokia 11");

        productService.createProduct(p);

        productService.getProduct(1l);
        assertEquals("Nokia 11",p.getName());


        p.setName("Nokia 12");
        productService.updateProduct(1l, p);
        assertEquals("Nokia 12",p.getName());


    }
}
