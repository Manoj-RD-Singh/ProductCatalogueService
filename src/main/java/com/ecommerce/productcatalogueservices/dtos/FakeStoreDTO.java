package com.ecommerce.productcatalogueservices.dtos;

import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreDTO implements Serializable
{
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;
}
