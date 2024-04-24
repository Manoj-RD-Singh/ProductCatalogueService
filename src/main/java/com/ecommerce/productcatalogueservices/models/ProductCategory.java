package com.ecommerce.productcatalogueservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class ProductCategory extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<Product> products;
}
