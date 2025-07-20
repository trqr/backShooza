package com.shooza.demo.services;

import com.shooza.demo.models.CodePromo;
import com.shooza.demo.repositories.PromoCodeRepository;
import com.shooza.demo.utils.CheckRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodePromoService {

    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private CheckRoles checkRoles;

    public ResponseEntity<?> getPromos(String authHeader){
        if (checkRoles.isAdmin(authHeader)) {
            return ResponseEntity.ok(promoCodeRepository.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès interdit");
        }
    }

    public ResponseEntity<?> addPromo(String authHeader, CodePromo codepromo){
        if (checkRoles.isAdmin(authHeader)) {
            return ResponseEntity.ok(promoCodeRepository.save(codepromo));
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès interdit");
        }
    }

    public void deletePromos(String authHeader, List<Integer> ids){
        if (checkRoles.isAdmin(authHeader)){
            promoCodeRepository.deleteAllById(ids);
        }
    }
}
