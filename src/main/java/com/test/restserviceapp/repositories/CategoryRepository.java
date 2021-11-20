package com.test.restserviceapp.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.test.restserviceapp.components.Category;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface CategoryRepository extends ElasticsearchRepository<Category, Integer>{

    List<Category> findByName(String name);
    
}
