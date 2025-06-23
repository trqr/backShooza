package com.shooza.demo.services;

import com.shooza.demo.DTO.FavoriteDTO;
import com.shooza.demo.models.Favorite;
import com.shooza.demo.models.Product;
import com.shooza.demo.models.User;
import com.shooza.demo.repositories.FavoriteRepository;
import com.shooza.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<Product> getFavorites(int userId){
        return productRepository.findUserFavs(userId);
    }


    public ResponseEntity<?> addToFavorites(FavoriteDTO favoriteDTO){
        User newUser = new User();
        newUser.setId(favoriteDTO.getUserId());
        Product newProduct = new Product();
        newProduct.setId(favoriteDTO.getProductId());


        favoriteRepository.save(new Favorite(0, newUser, newProduct));

        return ResponseEntity.ok("Added to favorites");
    }

    @Transactional
    public ResponseEntity<?> removeFromFavorites(int productId, int userId){
        favoriteRepository.deleteByProductIdAndUserId(productId, userId);
        return ResponseEntity.ok("Removed from favorites");
    }

}
