package com.ecommerce.productcatalogueservices.dtos;

import com.ecommerce.productcatalogueservices.models.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private Double price;
    private ProductCategoryDTO categoryDTO;
    private Boolean isPrimeProduct;
}
