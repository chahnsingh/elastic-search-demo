package com.elasticsearchdemo.service;

import com.elasticsearchdemo.index.ProductIndex;
import org.springframework.http.ResponseEntity;


public interface ElasticService {
    ResponseEntity<?> createOrUpdateDocument(ProductIndex request);

    ResponseEntity<?> getDocumentById(String productId);

    ResponseEntity<?> deleteDocumentById(String productId);

    ResponseEntity<?> searchAllDocuments();
}
