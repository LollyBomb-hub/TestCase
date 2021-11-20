package com.test.restserviceapp.components;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "categories")
public class Category {
    
    @Id
    private Integer categoryId;

    @Field(type = FieldType.Text, name = "categoryname")
    private String categoryName;

    // Will be invalid if there are no such in xml
    @Field(type = FieldType.Integer, name = "parentid")
    private Integer parentCategoryId;

    public Category(Integer idArg, String categoryArg, Integer parentCategoryIdArg) {
        this.categoryId = idArg;
        this.categoryName = categoryArg;
        this.parentCategoryId = parentCategoryIdArg;
        return;
    }

}
