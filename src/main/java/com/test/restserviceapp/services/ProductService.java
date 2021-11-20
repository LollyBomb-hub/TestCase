package com.test.restserviceapp.services;

import java.util.List;

import com.test.restserviceapp.components.Product;
import com.test.restserviceapp.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepositoryArg) {
        super();
        this.productRepository = productRepositoryArg;
        return;
    }

    public void createProductIndexBulk(List<Product> products) {
        this.productRepository.saveAll(products);
    }

    public List<Product> findProductByName(String name) {
        return this.productRepository.findByProductname(name);
    }

    public List<Product> findProductByCategoryIdAndProductName(Integer id, String name) {
        return this.productRepository.findByCategoryidAndProductname(id, name);
    }
}