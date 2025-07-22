package com.shooza.demo.controllers;


import com.shooza.demo.DTO.ProductStatusRequest;
import com.shooza.demo.DTO.ProductStockRequest;
import com.shooza.demo.models.Product;
import com.shooza.demo.repositories.ProductRepository;
import com.shooza.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> getProducts(){
        return productService.findAll();
    }

    @GetMapping("/filters")
    public List<Product> getFilteredProducts(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String color
        ) {
        return productService.productFiltering(brand, color);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id){
        return productService.getById(id);
    }

    @PutMapping("/stock")
    public ResponseEntity<List<Product>> setProductStock(@RequestBody ProductStockRequest request){
        return ResponseEntity.ok(productService.setProductsStock(request));
    }

    @PutMapping("/status")
    public ResponseEntity<List<Product>> setProductStatus(@RequestBody ProductStatusRequest request){
        return ResponseEntity.ok(productService.setProductsStatus(request));
    }
}
