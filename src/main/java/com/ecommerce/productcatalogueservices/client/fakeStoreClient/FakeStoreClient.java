package com.ecommerce.productcatalogueservices.client.fakeStoreClient;

import com.ecommerce.productcatalogueservices.dtos.FakeStoreDTO;
import com.ecommerce.productcatalogueservices.models.Product;
import com.ecommerce.productcatalogueservices.models.ProductCategory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.nonNull;

@Component
public class FakeStoreClient {

    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreDTO postFakeStoreProduct(FakeStoreDTO fakeStoreDTORequest){
        RestTemplate restTemplate = restTemplateBuilder.build();
       return restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreDTORequest, FakeStoreDTO.class).getBody();

    }

    public FakeStoreDTO getProduct(Long productId){
        FakeStoreDTO fakeStoreDTO = null;
        try {
            //Call fake store api using rest template builder
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreDTO> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreDTO.class, productId);
            if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
                fakeStoreDTO = response.getBody();
            } else {
                throw new RuntimeException("Get Product fake store api response failed or response is null");
            }
        }catch(Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        return fakeStoreDTO;
    }

    public FakeStoreDTO[] getAllProducts() {
        FakeStoreDTO[] fakeStoreDTOS = null;
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreDTO[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreDTO[].class);
            if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
                fakeStoreDTOS = response.getBody();
            } else {
                throw new RuntimeException("Get All Product fake store api response failed or response is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        return fakeStoreDTOS;
    }

    public FakeStoreDTO deleteProduct(Long productId) {
        FakeStoreDTO fakeStoreDTO = null;
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreDTO> response = deleteForEntity("https://fakestoreapi.com/products/{id}", FakeStoreDTO.class, productId);
            if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
                fakeStoreDTO = response.getBody();
            } else {
                throw new RuntimeException("Delete Product fake store api response failed or response is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        return fakeStoreDTO;
    }

    public <T> ResponseEntity<T> deleteForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (ResponseEntity)nonNull((ResponseEntity)restTemplate.execute(url, HttpMethod.DELETE, requestCallback, responseExtractor, uriVariables));
    }

    private static <T> T nonNull(@Nullable T result) {
        Assert.state(result != null, "No result");
        return result;
    }

    public FakeStoreDTO putForFakeStoreProduct(Long productId, FakeStoreDTO fakeStoreDTO){
        FakeStoreDTO fakeStoreDTOResponse = null;
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreDTO> response = putForEntity("https://fakestoreapi.com/products/{id}", fakeStoreDTO, FakeStoreDTO.class, productId);
            if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
                fakeStoreDTOResponse = response.getBody();
            } else {
                throw new RuntimeException("Update Product fake store api response failed or response is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        return fakeStoreDTOResponse;
    }

    public <T> ResponseEntity<T> putForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (ResponseEntity)nonNull((ResponseEntity)restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables));
    }

}
