package com.ecommerce.productcatalogueservices.services;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import com.ecommerce.productcatalogueservices.repositories.ProductCategoryRepository;
import com.ecommerce.productcatalogueservices.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class StorageProductService implements  IProductService{

    private ProductRepository productRepository;

    private ProductCategoryRepository productCategoryRepository;

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
}
