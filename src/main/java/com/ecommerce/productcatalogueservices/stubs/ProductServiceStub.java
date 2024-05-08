package com.ecommerce.productcatalogueservices.stubs;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productServiceStub")
public class ProductServiceStub implements IProductService {

    private Map<Long, Product> hm;

    public ProductServiceStub(){
        hm = new HashMap<>();
    }
    @Override
    public Product getProduct(Long productId) {
        return hm.get(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List)hm.values();
    }

    @Override
    public Product createProduct(Product product) {
        hm.put(product.getId(), product);
        return hm.get(product.getId());
    }

    @Override
    public Product deleteProduct(Long id) {
        hm.remove(id);
        return hm.get(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        hm.put(id, product);
        return hm.get(id);
    }
}
