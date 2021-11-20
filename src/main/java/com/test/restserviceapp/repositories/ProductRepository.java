package com.test.restserviceapp.repositories;

import java.util.List;

import com.test.restserviceapp.components.Product;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, Integer>{

    List<Product> findByProductname(String name);

    List<Product> findByCategoryidAndProductname(Integer id, String name);
    
}
