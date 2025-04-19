package com.elasticsearchdemo.repository;

import com.elasticsearchdemo.index.ProductIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductIndex,String> {

}
