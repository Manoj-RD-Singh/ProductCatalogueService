package com.ecommerce.productcatalogueservices.controllers;


import com.ecommerce.productcatalogueservices.dtos.ProductDTO;
import com.ecommerce.productcatalogueservices.enums.BaseModelStatus;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import com.ecommerce.productcatalogueservices.services.IProductService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private IProductService productService;

    @Autowired
    public ProductController(@Qualifier("storageProductService") IProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
            List<Product> productList = new ArrayList<>();
            productList = productService.getAllProducts();
            return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId){
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            if(productId < 1){
                throw new IllegalArgumentException("Product id is not correct");
               // return new ResponseEntity<>("excepton", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Product product = productService.getProduct(productId);
            header.add("first name", "manoj");
            header.add("first name", "manoj");
            header.add("second name", "singh");
            return new ResponseEntity<>(product, header,HttpStatus.OK);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO productDTO){
        Product product = mapperProductDtoToProduct(productDTO);
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
       try{
           Product product = productService.updateProduct(id, mapperProductDtoToProduct(productDTO));
           return new ResponseEntity<>(product, HttpStatus.OK);
       }catch(Exception ex){
           ex.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long productId){
       try{
           Product product = productService.deleteProduct(productId);
           return new ResponseEntity<Product>(product, HttpStatus.OK);
       }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    private Product mapperProductDtoToProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setImageURL(productDTO.getImageURL());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setIsPrimeProduct(productDTO.getIsPrimeProduct());
        if(productDTO.getCategoryDTO() != null){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(productDTO.getCategoryDTO().getId());
            productCategory.setName(productDTO.getCategoryDTO().getName());
            productCategory.setDescription(productDTO.getCategoryDTO().getDescription());
            product.setCategory(productCategory);
        }
        return product;
    }




}
