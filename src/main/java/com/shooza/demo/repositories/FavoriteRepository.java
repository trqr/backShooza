package com.shooza.demo.repositories;

import com.shooza.demo.models.Favorite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    void deleteByProductIdAndUserId(int productId, int userId);

}
