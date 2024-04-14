package com.ecommerce.productcatalogueservices.controllers;


import com.ecommerce.productcatalogueservices.dtos.ProductDTO;
import com.ecommerce.productcatalogueservices.enums.BaseModelStatus;
import com.ecommerce.productcatalogueservices.models.Product;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        Product p = new Product();
        p.setId(100l);
        p.setName("OnePlus");
        p.setPrice(25000.00);
        productList.add(p);
        return productList;
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") Long productId){
        Product p = new Product();
        p.setId(productId);
        p.setId(200l);
        p.setName("SoneyTV");
        p.setPrice(100000.00);
        return p;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO productDTO){
        Product p = new Product();
        p.setId(300l);
        p.setName(productDTO.getName());
        p.setDescription(productDTO.getDescription());
        p.setPrice(productDTO.getPrice());
        return p;
    }

    @PutMapping
    public Product updateProduct(@RequestBody ProductDTO productDTO){
        Product p = new Product();
        p.setId(productDTO.getId());
        p.setName(productDTO.getName());
        p.setDescription(productDTO.getDescription());
        p.setPrice(productDTO.getPrice());
        return p;
    }

    @DeleteMapping
    public Product deleteProduct(@RequestBody ProductDTO productDTO){
        Product p = new Product();
        p.setId(productDTO.getId());
        p.setName(productDTO.getName());
        p.setDescription(productDTO.getDescription());
        p.setPrice(productDTO.getPrice());
        p.setStatus(BaseModelStatus.INACTIVE);
        return p;
    }


}
