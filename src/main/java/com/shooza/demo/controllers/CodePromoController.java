package com.shooza.demo.controllers;

import com.shooza.demo.models.CodePromo;
import com.shooza.demo.repositories.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/codepromo")
public class CodePromoController {

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    @PostMapping("")
    public CodePromo comparePromoCode(@RequestBody String promoCode){
        System.out.println("test");
        return promoCodeRepository.findByCode(promoCode);
        }
}
