package com.elasticsearchdemo.controller;

import com.elasticsearchdemo.index.ProductIndex;
import com.elasticsearchdemo.service.ElasticService;
import com.elasticsearchdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/product")
public class ProductController {

    private final ProductService productService;
    private final ElasticService elasticService;

    @Autowired
    public ProductController(ProductService productService, ElasticService elasticService) {
        this.productService = productService;
        this.elasticService = elasticService;
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping("/save")
    ResponseEntity<?> saveProduct(@RequestBody ProductIndex request) {
        return this.productService.saveProduct(request);
    }

    @PostMapping("/createOrUpdateDocument")
    public ResponseEntity<?> elasticService(@RequestBody ProductIndex product) {
        return elasticService.createOrUpdateDocument(product);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<?> getDocumentById(@RequestParam String productId) {
        return elasticService.getDocumentById(productId);
    }

    @DeleteMapping("/deleteDocument")
    public ResponseEntity<?> deleteDocumentById(@RequestParam String productId) {
        return elasticService.deleteDocumentById(productId);
    }

    @GetMapping("/searchDocument")
    public ResponseEntity<?> searchAllDocument() {
        return elasticService.searchAllDocuments();
    }

}
