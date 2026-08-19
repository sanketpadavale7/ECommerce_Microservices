package com.projects.product_service.Service;


import com.projects.product_service.DTO.ProductRequest;
import com.projects.product_service.DTO.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ResponseEntity<List<ProductResponse>> getAllProducts();

    ResponseEntity<ProductResponse> getProductById(Long id);

    ResponseEntity<String> addProduct(ProductRequest productRequest);

    ResponseEntity<String> deleteProduct(Long id);
}
