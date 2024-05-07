package com.ecommerce.productcatalogueservices.repositories;

import com.ecommerce.productcatalogueservices.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Getter
@Setter
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void productJpaQueryFindTest(){
        List<Product> products = productRepository.findProductByPriceBetween(30.00, 50.00);
        for(Product p : products){
            System.out.println(p.getName()+" "+p.getPrice());
        }

        List<Product> products1 = productRepository.findProductByIsPrimeProductTrue();
        System.out.println(products1.size());

        List<Product> products2 = productRepository.findProductByOrderByIdDesc();
        for(Product p : products2){
            System.out.println(p.getId()+" "+p.getName());
        }

    }

    @Test
    public void jpaQueryTest(){
        String name = productRepository.findProductNameById(1l);
        System.out.println(name);

        List<String> prices =  productRepository.findProductPriceByPriceGreaterThan(30.00);
        prices.forEach(price -> System.out.println((price)));

        String catName = productRepository.findCategoryNameByProductId(2l);
        System.out.println(catName);
    }

    @Test
    public void flakyTest(){
        Random random = new Random();
        int next = random.nextInt(10);
        assert (next<5);
    }

}