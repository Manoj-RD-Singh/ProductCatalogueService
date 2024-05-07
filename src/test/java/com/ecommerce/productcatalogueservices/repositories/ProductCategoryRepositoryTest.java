package com.ecommerce.productcatalogueservices.repositories;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    @Rollback(value = true) // Bydefaul it is true so that Db changes rollbacked
    public void testLoding(){
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(2l);
        if(productCategoryOptional.isPresent()){
            System.out.println( productCategoryOptional.get().getName());
            List<Product> products = productCategoryOptional.get().getProducts();
            for(Product p : products){
                System.out.println(p.getName());
            }
        }


    }

    @Test
    @Transactional
    public void testFetchMode(){
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(2l);
        System.out.println("Second query if any");
       /* if(productCategoryOptional.isPresent()){
            System.out.println( productCategoryOptional.get().getName());
            List<Product> products = productCategoryOptional.get().getProducts();
            for(Product p : products){
                System.out.println(p.getName());
            }
        }*/
    }

    @Test
    @Transactional
    public void getAllCategoryAndProduct(){
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        for(ProductCategory pc : productCategories){
            System.out.println(pc.getName());
            List<Product> products = pc.getProducts();
            for(Product p : products){
                System.out.println(p.getName());
            }
        }
    }

}