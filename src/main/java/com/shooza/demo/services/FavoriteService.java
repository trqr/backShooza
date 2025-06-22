package com.shooza.demo.services;

import com.shooza.demo.models.Product;
import com.shooza.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getFavorites(int userId){
        return productRepository.findUserFavs(userId);
    }
}
