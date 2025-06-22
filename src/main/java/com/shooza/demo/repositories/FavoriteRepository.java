package com.shooza.demo.repositories;

import com.shooza.demo.models.Favorite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

  /*  @Modifying
    @Transactional
    @Query("INSERT INTO Favorite(f.user_id, f.product_id) VALUES (:productId, :userId)")
            void  addNewFav(@Param("userId") int productId, @Param("userId") int userId ); */
}
