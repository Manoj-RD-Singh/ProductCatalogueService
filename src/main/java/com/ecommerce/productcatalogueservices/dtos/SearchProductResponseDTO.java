package com.ecommerce.productcatalogueservices.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private Double price;
}
