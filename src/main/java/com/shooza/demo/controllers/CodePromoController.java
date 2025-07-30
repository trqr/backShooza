package com.shooza.demo.controllers;

import com.shooza.demo.DTO.PromoCodeRequest;
import com.shooza.demo.DTO.PromoCodeResponse;
import com.shooza.demo.configuration.AdminOnly;
import com.shooza.demo.exceptions.InvalidPromoCodeException;
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
    private CodePromoService codePromoService;

    @GetMapping("/all")
    @AdminOnly
    public ResponseEntity<?> getPromos(){
        return codePromoService.getPromos();
    }

    @PostMapping("/add")
    @AdminOnly
    public ResponseEntity<?> addPromo(@Valid @RequestBody CodePromo codepromo){
        return codePromoService.addPromo(codepromo);
    }

    @PostMapping("/delete")
    @AdminOnly
    public void deletePromos(@RequestBody List<Integer> ids){
        codePromoService.deletePromos(ids);
    }

    @PostMapping
    public ResponseEntity<PromoCodeResponse> validatePromoCode(@RequestBody PromoCodeRequest request) {
        return ResponseEntity.ok(codePromoService.validatePromoCode(request));
    }
}
