package com.ecommerce.productcatalogueservices.controllers;

import com.ecommerce.productcatalogueservices.dtos.ProductDTO;
import com.ecommerce.productcatalogueservices.dtos.SearchProductRequestDTO;
import com.ecommerce.productcatalogueservices.dtos.SearchProductResponseDTO;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import com.ecommerce.productcatalogueservices.services.ISearchProductService;
import com.ecommerce.productcatalogueservices.services.SearchProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchProductController {

    private ISearchProductService searchProductService;

    public SearchProductController(ISearchProductService searchProductService){
        this.searchProductService = searchProductService;
    }

    @PostMapping("/product")
    public ResponseEntity<Page<Product>> searchProduct(@RequestBody SearchProductRequestDTO searchProductRequestDTO){
       /* Page<Product> products = searchProductService.searchProduct(searchProductRequestDTO.getName(), searchProductRequestDTO.getPageNumber(), searchProductRequestDTO.getPageSize(), searchProductRequestDTO.getSortParams());
        Page<SearchProductResponseDTO> searchProductResponseDTOS = Page.empty();
        for(Product product : products){
            searchProductResponseDTOS.and(mapperProductToSearchProductResponseDTO(product));
        }*/
        return new ResponseEntity<>(searchProductService.searchProduct(searchProductRequestDTO.getName(), searchProductRequestDTO.getPageNumber(), searchProductRequestDTO.getPageSize(), searchProductRequestDTO.getSortParams())
                , HttpStatus.OK);
    }

    private SearchProductResponseDTO mapperProductToSearchProductResponseDTO(Product product){
        SearchProductResponseDTO searchProductResponseDTO = new SearchProductResponseDTO();
        searchProductResponseDTO.setId(product.getId());
        searchProductResponseDTO.setName(product.getName());
        searchProductResponseDTO.setImageURL(product.getImageURL());
        searchProductResponseDTO.setPrice(product.getPrice());
        searchProductResponseDTO.setDescription(product.getDescription());
        return searchProductResponseDTO;
    }
}
