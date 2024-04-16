package com.ecommerce.productcatalogueservices.controllers;


import com.ecommerce.productcatalogueservices.dtos.FakeStoreDTO;
import com.ecommerce.productcatalogueservices.dtos.ProductDTO;
import com.ecommerce.productcatalogueservices.enums.BaseModelStatus;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.services.IProductService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            List<Product> productList = new ArrayList<>();
            productList = productService.getAllProducts();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId){
        try {
            if(productId < 1){
                throw new IllegalArgumentException("Product id is not correct");
            }
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);

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
