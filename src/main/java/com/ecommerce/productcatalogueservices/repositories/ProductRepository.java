package com.ecommerce.productcatalogueservices.repositories;

import com.ecommerce.productcatalogueservices.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    public Product save(Product product);

    @Override
    public Optional<Product> findById(Long id);
}
