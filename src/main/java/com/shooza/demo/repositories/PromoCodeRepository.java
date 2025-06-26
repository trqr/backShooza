package com.shooza.demo.repositories;

import com.shooza.demo.models.CodePromo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoCodeRepository extends JpaRepository<CodePromo, Integer> {
    Optional<CodePromo> findByCode(String code);
}
