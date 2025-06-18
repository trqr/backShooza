package com.shooza.demo.controllers;


import com.shooza.demo.models.Product;
import com.shooza.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/products-filters")
    public List<Product> getFilteredProducts(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String color
    ) {
        return productRepository.findWithFilters(brand, color);
    }
}
