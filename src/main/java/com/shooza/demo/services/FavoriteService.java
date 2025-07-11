package com.shooza.demo.services;

import com.shooza.demo.DTO.FavoriteDTO;
import com.shooza.demo.models.Favorite;
import com.shooza.demo.models.Product;
import com.shooza.demo.models.User;
import com.shooza.demo.repositories.FavoriteRepository;
import com.shooza.demo.repositories.ProductRepository;
import com.shooza.demo.repositories.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    public List<Product> getFavorites(int userId){
        return productRepository.findUserFavs(userId);
    }


    public ResponseEntity<?> addToFavorites(FavoriteDTO favoriteDTO){

        favoriteRepository.save(new Favorite(
                0,
                userRepository.getReferenceById(favoriteDTO.getUserId()),
                productRepository.getReferenceById(favoriteDTO.getProductId())
        ));

        return ResponseEntity.ok("Added to favorites");
    }

    @Transactional
    public ResponseEntity<?> removeFromFavorites(int productId, int userId){
        favoriteRepository.deleteByProductIdAndUserId(productId, userId);
        return ResponseEntity.ok("Removed from favorites");
    }

}
