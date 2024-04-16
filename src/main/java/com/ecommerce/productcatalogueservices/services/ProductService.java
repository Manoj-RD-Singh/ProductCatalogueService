package com.ecommerce.productcatalogueservices.services;

import com.ecommerce.productcatalogueservices.dtos.FakeStoreDTO;
import com.ecommerce.productcatalogueservices.dtos.ProductDTO;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Product getProduct(Long productId){

            //Call fake store api using rest template builder
            RestTemplate restTemplate = restTemplateBuilder.build();
            FakeStoreDTO fakeStoreDTO = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreDTO.class, productId).getBody();

        assert fakeStoreDTO != null;
        return convertFakeStoreToProduct(fakeStoreDTO);

    }

    public List<Product> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreDTO [] fakeStoreDTOS = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreDTO[].class).getBody();

        List<Product> products = new ArrayList<>();
        if(fakeStoreDTOS == null || fakeStoreDTOS.length == 0){
            return null;
        }
        for(FakeStoreDTO fakeStoreDTO : fakeStoreDTOS){
            products.add(convertFakeStoreToProduct(fakeStoreDTO));
        }
        return products;
    }

    public Product createProduct(ProductDTO productDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreDTO fakeStoreDTO = restTemplate.postForEntity("https://fakestoreapi.com/products", productDTO, FakeStoreDTO.class).getBody();
        return convertFakeStoreToProduct(fakeStoreDTO);
    }

    private Product convertFakeStoreToProduct(FakeStoreDTO fakeStoreDTO) {
        Product product = new Product();
        product.setId(fakeStoreDTO.getId());
        product.setName(fakeStoreDTO.getTitle());
        product.setPrice(fakeStoreDTO.getPrice());
        product.setImageURL(fakeStoreDTO.getImage());

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(fakeStoreDTO.getCategory());
        product.setCategory(productCategory);

        return product;
    }

}
