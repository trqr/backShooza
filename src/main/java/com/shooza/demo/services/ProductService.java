package com.shooza.demo.services;

import com.shooza.demo.DTO.ProductStockRequest;
import com.shooza.demo.models.Product;
import com.shooza.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> productFiltering(String brand, String color){
        return productRepository.findWithFilters(brand, color);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product getById(int id) {
        return this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non dispo"));
    }

    public List<Product> setProductsStock(ProductStockRequest request){
        List<Product> products = productRepository.findAllById(request.getIds());
        for(Product product: products){
            product.setStock(product.getStock()+request.getAddingStockValue());
        }
        return productRepository.saveAll(products);
    }
}
