package com.ecommerce.productcatalogueservices.services;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.SortParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchProductService {

    Page<Product> searchProduct(String productName, int pageNumber, int pageSize, List<SortParam> sortParams);
}
