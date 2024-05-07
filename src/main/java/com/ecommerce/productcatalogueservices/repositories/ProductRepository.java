package com.ecommerce.productcatalogueservices.repositories;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    public Product save(Product product);

    @Override
    public Optional<Product> findById(Long id);


    List<Product> findByCategory(ProductCategory pc);

    List<Product> findProductByPriceBetween(Double low, Double high);

    List<Product> findProductByIsPrimeProductTrue();

    List<Product> findProductByOrderByIdDesc();

    @Query("select p.name from Product p where p.id=?1")
    String findProductNameById(Long id);

    @Query("select p.name from Product p where p.price >:priceVal")
    List<String> findProductPriceByPriceGreaterThan(@Param("priceVal") Double price);

    @Query("select c.name from ProductCategory c join Product p on c.id=p.category.id and p.id=?1")
    String findCategoryNameByProductId(Long productId);
}
