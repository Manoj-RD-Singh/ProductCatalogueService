package com.ecommerce.productcatalogueservices.services;

import com.ecommerce.productcatalogueservices.dtos.FakeStoreDTO;
import com.ecommerce.productcatalogueservices.dtos.ProductDTO;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface IProductService {
    Product getProduct(Long productId);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product deleteProduct(Long id);

    Product updateProduct(Long id, Product product);

    Product getProductDetailsByProductIdAndUserId(Long productId, Long userId);
}
