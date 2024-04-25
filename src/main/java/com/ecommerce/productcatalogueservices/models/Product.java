package com.ecommerce.productcatalogueservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private String imageURL;
    private Double price;
    //1:1
    //M:1
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductCategory category;
}
