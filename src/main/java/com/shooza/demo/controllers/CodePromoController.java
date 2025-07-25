package com.shooza.demo.controllers;

import com.shooza.demo.DTO.PromoCodeRequest;
import com.shooza.demo.DTO.PromoCodeResponse;
import com.shooza.demo.models.CodePromo;
import com.shooza.demo.repositories.PromoCodeRepository;
import com.shooza.demo.services.CodePromoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/codepromo")
public class CodePromoController {

    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private CodePromoService codePromoService;

    @GetMapping("/all")
    public ResponseEntity<?> getPromos(@RequestHeader("Authorization") String authHeader){
        return codePromoService.getPromos(authHeader);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPromo(@RequestHeader("Authorization") String authHeader,@Valid @RequestBody CodePromo codepromo){
        return codePromoService.addPromo(authHeader, codepromo);
    }

    @PostMapping("/delete")
    public void deletePromos(@RequestHeader("Authorization") String authHeader, @RequestBody List<Integer> ids){
        codePromoService.deletePromos(authHeader, ids);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PromoCodeResponse> validatePromoCode(@RequestBody PromoCodeRequest request) {
        Optional<CodePromo> optionalCodePromo = promoCodeRepository.findByCode(request.getCode());

        if (optionalCodePromo.isPresent()) {
            CodePromo codePromo = optionalCodePromo.get();
            return ResponseEntity.ok(new PromoCodeResponse(true, "Code promo appliqué avec succès.", codePromo.getPercentage()));
        } else {
            return ResponseEntity.ok(new PromoCodeResponse(false, "Le code promo est invalide.", 0));
        }
    }
}
