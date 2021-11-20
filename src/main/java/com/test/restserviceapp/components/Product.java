package com.test.restserviceapp.components;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products")
public class Product {
    
    @Id
    private Integer productId;

    @Field(type = FieldType.Integer, name = "categoryid")
    private Integer categoryId;

    @Field(type = FieldType.Text, name = "productname")
    private String productName;

    @Field(type = FieldType.Text, name = "productdescription")
    private String productDescription;

    @Field(type = FieldType.Integer, name = "productprice")
    private Integer productPrice;

    @Field(type = FieldType.Text, name = "productimageurl")
    private String productImageUrl;

    public Product(Integer productIdArg, Integer categoryIdArg,
                    String productNameArg, String productDescriptionArg,
                    Integer productPriceArg, String productImageUrlArg) {
        this.productId = productIdArg;
        this.categoryId = categoryIdArg;
        this.productName = productNameArg;
        this.productDescription = productDescriptionArg;
        this.productPrice = productPriceArg;
        this.productImageUrl = productImageUrlArg;
    }
}
