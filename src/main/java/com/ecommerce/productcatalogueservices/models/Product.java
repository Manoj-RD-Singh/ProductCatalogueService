package com.ecommerce.productcatalogueservices.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private String imageURL;
    private Double price;
    private Boolean isPrimeProduct;
    //1:1
    //M:1
    //@JsonBackReference
    @JsonManagedReference
   /* @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")*/
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductCategory category;
}
