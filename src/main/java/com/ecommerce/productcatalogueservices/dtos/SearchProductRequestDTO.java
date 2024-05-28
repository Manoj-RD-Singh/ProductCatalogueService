package com.ecommerce.productcatalogueservices.dtos;

import com.ecommerce.productcatalogueservices.models.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchProductRequestDTO {
    private Long id;
    private String name;
    private int pageNumber;
    private int pageSize;
    private List<SortParam> sortParams;

}
