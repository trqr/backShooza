package com.shooza.demo.controllers;

import com.shooza.demo.DTO.FavoriteDTO;
import com.shooza.demo.DTO.UserDTO;
import com.shooza.demo.models.Favorite;
import com.shooza.demo.models.Product;
import com.shooza.demo.services.FavoriteService;
import com.shooza.demo.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fav")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("")
    public List<Product> getFavorites(@RequestParam int userId){
        return favoriteService.getFavorites(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToFavorites(@RequestParam int productId, @RequestParam int userId){
        FavoriteDTO favoriteDTO = new FavoriteDTO(productId, userId);
        return favoriteService.addToFavorites(favoriteDTO);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromFavorites(@RequestParam int productId, @RequestParam int userId) {
        return favoriteService.removeFromFavorites(productId, userId);
    }

}
