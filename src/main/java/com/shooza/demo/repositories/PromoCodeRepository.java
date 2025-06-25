package com.shooza.demo.repositories;

import com.shooza.demo.models.CodePromo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeRepository extends JpaRepository<CodePromo, Integer> {
    public CodePromo findByCode(String code);
}
