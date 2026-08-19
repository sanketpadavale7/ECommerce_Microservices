package com.projects.product_service.Controller;


import com.projects.product_service.DTO.ProductRequest;
import com.projects.product_service.DTO.ProductResponse;
import com.projects.product_service.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products/")
public class ProductController
{
    @Autowired
    ProductService service;


    @GetMapping("getAllProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts()
    {

        return service.getAllProducts();

    }

    @GetMapping("getProduct/{Id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long Id)
    {

        return service.getProductById(Id);
    }

    @PostMapping("addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest)
    {
        return service.addProduct(productRequest);
    }

    @DeleteMapping("deleteProduct/{Id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long Id)
    {
        return service.deleteProduct(Id);

    }




}


