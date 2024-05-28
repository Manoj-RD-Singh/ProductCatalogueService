package com.ecommerce.productcatalogueservices.services;

import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.SortParam;
import com.ecommerce.productcatalogueservices.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements ISearchProductService{

    private ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Page<Product> searchProduct(String productName, int pageNumber, int pageSize, List<SortParam> sortParams) {
       // Sort sort = Sort.by("price").descending();

        Sort sort = null;
        for(int i=0; i<sortParams.size(); i++){
            if(!sortParams.isEmpty()){
                if(i==0){
                    sort = Sort.by(Sort.Direction.valueOf(sortParams.get(i).getSortType()), sortParams.get(i).getParamName());
                }else{
                    sort = sort.and(Sort.by(Sort.Direction.valueOf(sortParams.get(i).getSortType()), sortParams.get(i).getParamName()));
                }
            }

        }
       // Sort sort = Sort.by(Sort.Direction.valueOf(sortParams.get(0).getSortType()), sortParams.get(0))
        Page<Product> productList = null;
        if(sort != null){
            productList = productRepository.findByNameContainsIgnoreCase(productName, PageRequest.of(pageNumber, pageSize, sort));
        }else{
            productList = productRepository.findByNameContainsIgnoreCase(productName, PageRequest.of(pageNumber, pageSize));
        }

        return productList;
    }
}
