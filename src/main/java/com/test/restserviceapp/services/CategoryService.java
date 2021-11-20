package com.test.restserviceapp.services;

import java.util.List;

import com.test.restserviceapp.components.Category;
import com.test.restserviceapp.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepositoryArg) {
        super();
        this.categoryRepository = categoryRepositoryArg;
        return;
    }

    public void createCategoryIndexBulk(final List<Category> categories) {
        this.categoryRepository.saveAll(categories);
    }

}
