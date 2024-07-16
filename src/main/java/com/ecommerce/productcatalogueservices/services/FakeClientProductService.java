package com.ecommerce.productcatalogueservices.services;

import com.ecommerce.productcatalogueservices.client.fakeStoreClient.FakeStoreClient;
import com.ecommerce.productcatalogueservices.dtos.FakeStoreDTO;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreService")
@Slf4j
public class FakeClientProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    private RedisTemplate<String, Object> redisTemplate;

    public FakeClientProductService(RestTemplateBuilder restTemplateBuilder,
                                    FakeStoreClient fakeStoreClient,RedisTemplate<String, Object> redisTemplate){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }

    public Product getProduct(Long productId) {
        //check in redis cache first
        FakeStoreDTO fakeStoreDTO = null;
        fakeStoreDTO = (FakeStoreDTO) redisTemplate.opsForHash().get("PRODUCTS", productId);
        if(fakeStoreDTO != null){
            log.info("Found in cache : "+productId);
            return convertFakeStoreToProduct(fakeStoreDTO);
        }
        //if not found in cache - get from fakestore and put in cache
       fakeStoreDTO = fakeStoreClient.getProduct(productId);
       Product product = null;
       if(fakeStoreDTO != null){
           product = convertFakeStoreToProduct(fakeStoreDTO);
       }
       redisTemplate.opsForHash().put("PRODUCTS", productId, fakeStoreDTO);

       return product;

    }

    public Product updateProduct(Long id, Product product){

        FakeStoreDTO fakeStoreDTO = fakeStoreClient.putForFakeStoreProduct(id, mapperProductToFakeStoreDTO(product));
        return convertFakeStoreToProduct(fakeStoreDTO);
    }

    public List<Product> getAllProducts() {
        FakeStoreDTO[] fakeStoreDTOS = fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        if (fakeStoreDTOS == null || fakeStoreDTOS.length == 0) {
            return null;
        }
        for (FakeStoreDTO fakeStoreDTO : fakeStoreDTOS) {
            products.add(convertFakeStoreToProduct(fakeStoreDTO));
        }
        return products;
    }

    public Product createProduct(Product product){
        FakeStoreDTO fakeStoreDTORequest = mapperProductToFakeStoreDTO(product);
        return convertFakeStoreToProduct(fakeStoreClient.postFakeStoreProduct(fakeStoreDTORequest));
    }

    public Product deleteProduct(Long productId){
        FakeStoreDTO fakeStoreDTO = fakeStoreClient.deleteProduct(productId);
        Product product = null;
        if(fakeStoreDTO != null){
            product = convertFakeStoreToProduct(fakeStoreDTO);
        }
        return product;
    }

    private FakeStoreDTO mapperProductToFakeStoreDTO(Product product) {
        FakeStoreDTO fakeStoreDTO = new FakeStoreDTO();
        fakeStoreDTO.setId(product.getId());
        fakeStoreDTO.setTitle(product.getName());
        fakeStoreDTO.setPrice(product.getPrice());
        fakeStoreDTO.setImage(product.getImageURL());
        if(product.getCategory() != null){
            fakeStoreDTO.setCategory(product.getCategory().getName());
        }
        return fakeStoreDTO;
    }

    private Product convertFakeStoreToProduct(FakeStoreDTO fakeStoreDTO) {
        Product product = new Product();
        product.setId(fakeStoreDTO.getId());
        product.setName(fakeStoreDTO.getTitle());
        product.setPrice(fakeStoreDTO.getPrice());
        product.setImageURL(fakeStoreDTO.getImage());

        if(fakeStoreDTO.getCategory() != null){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setName(fakeStoreDTO.getCategory());
            product.setCategory(productCategory);
        }
        return product;
    }

    public Product getProductDetailsByProductIdAndUserId(Long productId, Long userId){
        return null;
    }

}
