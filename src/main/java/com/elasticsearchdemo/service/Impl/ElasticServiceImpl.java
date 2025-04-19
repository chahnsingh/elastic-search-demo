package com.elasticsearchdemo.service.Impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.elasticsearchdemo.index.ProductIndex;
import com.elasticsearchdemo.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ElasticServiceImpl implements ElasticService {
    @Autowired
    private ElasticsearchClient elasticsearchClient;


    private final String indexName = "product_index";

    @Override
    public ResponseEntity<?> createOrUpdateDocument(ProductIndex request) {
        try {
            IndexResponse response = elasticsearchClient.index(i -> i
                    .index(indexName)
                    .id(String.valueOf(request.getId()))
                    .document(request)
            );
            if (response.result().name().equals("Created")) {
                return new ResponseEntity<>("Document has been successfully created.", HttpStatus.OK);
            } else if (response.result().name().equals("Updated")) {
                return new ResponseEntity<>("Document has been successfully updated.", HttpStatus.OK);
            }
            return new ResponseEntity<>("Error while performing the operation.", HttpStatus.NOT_FOUND);
        } catch (ElasticsearchException | IOException e) {
            return new ResponseEntity<>(Map.of("msg", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> getDocumentById(String productId) {
        ProductIndex product = null;
        try {
            GetResponse<ProductIndex> response = elasticsearchClient.get(g -> g
                            .index(indexName)
                            .id(productId),
                    ProductIndex.class
            );

            if (response.found()) {
                product = response.source();
                assert product != null;
                System.out.println("Product name " + product.getProductName());
            } else {
                System.out.println("Product not found");
            }
            return new ResponseEntity<>(product, HttpStatus.OK);

        } catch (ElasticsearchException | IOException e) {
            return new ResponseEntity<>(Map.of("msg", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteDocumentById(String productId) {
        try {
            DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(productId));
            DeleteResponse deleteResponse = elasticsearchClient.delete(request);
            if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
                return new ResponseEntity<>("Product with id " + deleteResponse.id() + " has been deleted.", HttpStatus.OK);
            }
            System.out.println("Product not found");
            return new ResponseEntity<>("Product with id " + deleteResponse.id() + " does not exist.", HttpStatus.OK);
        } catch (ElasticsearchException | IOException e) {
            return new ResponseEntity<>(Map.of("msg", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public ResponseEntity<?> searchAllDocuments() {
        try {
            SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
            SearchResponse<ProductIndex> searchResponse = elasticsearchClient.search(searchRequest, ProductIndex.class);
            List<ProductIndex> productIndexList = searchResponse.hits()
                    .hits().
                    stream().
                    map(Hit::source).
                    peek(System.out::println).collect(Collectors.toList());
            return new ResponseEntity<>(productIndexList, HttpStatus.OK);
        } catch (ElasticsearchException | IOException e) {
            return new ResponseEntity<>(Map.of("msg", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
