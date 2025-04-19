package com.elasticsearchdemo.service.Impl;

import com.elasticsearchdemo.index.ProductIndex;
import com.elasticsearchdemo.repository.ProductRepository;
import com.elasticsearchdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<?> findAllProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveProduct(ProductIndex request) {
        return new ResponseEntity<>(productRepository.save(request),HttpStatus.OK);
    }
}
