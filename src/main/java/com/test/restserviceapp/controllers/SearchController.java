package com.test.restserviceapp.controllers;

import java.util.List;

import com.test.restserviceapp.components.Product;
import com.test.restserviceapp.services.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SearchController {

    private ProductService productService;

    public SearchController(ProductService productRepositoryArg) {
        this.productService = productRepositoryArg;
        return;
    }

    @GetMapping("/fetch")
    @ResponseBody
    public List<Product> fetch(@RequestParam(required = true, name = "i") Integer id,
                                @RequestParam(required = true, name = "a") String name) {
        return this.productService.findProductByCategoryIdAndProductName(id, name);
    }
    
}
