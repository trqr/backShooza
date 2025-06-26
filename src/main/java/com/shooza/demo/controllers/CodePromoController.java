package com.shooza.demo.controllers;

import com.shooza.demo.DTO.PromoCodeRequest;
import com.shooza.demo.DTO.PromoCodeResponse;
import com.shooza.demo.models.CodePromo;
import com.shooza.demo.repositories.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/codepromo")
public class CodePromoController {

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @PostMapping
    public ResponseEntity<PromoCodeResponse> validatePromoCode(@RequestBody PromoCodeRequest request) {
        String code = request.getCode();

        Optional<CodePromo> optionalCodePromo = promoCodeRepository.findByCode(code);

        if (optionalCodePromo.isPresent()) {
            CodePromo codePromo = optionalCodePromo.get();
            return ResponseEntity.ok(new PromoCodeResponse(true, "Code promo appliqué avec succès.", codePromo.getPercentage()));
        } else {
            return ResponseEntity.ok(new PromoCodeResponse(false, "Le code promo est invalide.", 0));
        }
    }
}
