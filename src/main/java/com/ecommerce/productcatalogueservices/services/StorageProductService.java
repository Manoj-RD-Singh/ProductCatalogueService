package com.ecommerce.productcatalogueservices.services;

import com.ecommerce.productcatalogueservices.dtos.UserDto;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import com.ecommerce.productcatalogueservices.repositories.ProductCategoryRepository;
import com.ecommerce.productcatalogueservices.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

//@Primary
@Service("storageProductService")
public class StorageProductService implements  IProductService{

    private ProductRepository productRepository;

    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    public StorageProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository){

        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }
    @Override
    public Product getProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            return null;
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
      /*  ProductCategory productCategory = null;
        if(product.getCategory() != null){
            productCategory = productCategoryRepository.findByName(product.getCategory().getName());
            if(productCategory != null){
                product.setCategory(productCategory);
            }
        }*/

        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    public Product getProductDetailsByProductIdAndUserId(Long productId, Long userId){
        //get User from user microservice
        UserDto userDto = restTemplate.getForEntity("http://UserAuthenticationService/users/{id}", UserDto.class, userId).getBody();
        if(userDto == null){
            throw new RuntimeException("User not found with id: "+userId);
        }
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Product not found with id: "+productId);
        }
        return productOptional.get();
    }
}
