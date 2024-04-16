package com.ecommerce.productcatalogueservices.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCategory extends BaseModel{
    private String name;
    private String description;
    private List<Product> products;
}
