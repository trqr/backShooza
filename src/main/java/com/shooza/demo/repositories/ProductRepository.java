package com.shooza.demo.repositories;

import com.shooza.demo.DTO.UserDTO;
import com.shooza.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p " +
            "WHERE (:brand IS NULL OR :brand = '' OR p.brand = :brand) " +
            "AND (:color IS NULL OR :color = '' OR p.color LIKE %:color%)")
    List<Product> findWithFilters(
            @org.springframework.lang.Nullable String brand,
            @org.springframework.lang.Nullable String color
    );

    @Query("SELECT f.product FROM Favorite f WHERE f.user.id = :userId")
    List<Product> findUserFavs(@Param("userId") int userId);
}


