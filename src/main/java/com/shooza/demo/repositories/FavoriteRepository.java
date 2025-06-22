package com.shooza.demo.repositories;

import com.shooza.demo.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

}
