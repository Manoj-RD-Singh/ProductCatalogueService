package com.ecommerce.productcatalogueservices.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
@Entity
public class ProductCategory extends BaseModel{
    //@Column(unique = true)
    @JsonIgnore
    private Long id;
    private String name;
    private String description;

    //@JsonManagedReference
    @JsonBackReference
    //@JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 3)
    private List<Product> products;
}
