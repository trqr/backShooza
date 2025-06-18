package com.shooza.demo.repositories;

import com.shooza.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p " +
            "WHERE p.brand = :brand AND p.color LIKE %:color%")
    List<Product> findWithFilters(
            @org.springframework.lang.Nullable String brand,
            @org.springframework.lang.Nullable String color
    );

}


