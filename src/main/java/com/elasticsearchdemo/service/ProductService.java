package com.elasticsearchdemo.service;

import com.elasticsearchdemo.index.ProductIndex;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> findAllProducts();
    ResponseEntity<?> saveProduct(ProductIndex request);


}
