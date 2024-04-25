package com.ecommerce.productcatalogueservices.repositories;

import com.ecommerce.productcatalogueservices.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    public ProductCategory findByName(String name);
}
