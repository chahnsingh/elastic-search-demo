package com.elasticsearchdemo.index;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "product_index")
public class ProductIndex {
    @Id
    private String id;
    @Field(type = FieldType.Text,name = "productName")
    private String productName;
    @Field(type = FieldType.Double,name = "price")
    private double qty;

}
