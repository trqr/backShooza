package com.shooza.demo.services;

import com.shooza.demo.DTO.PromoCodeRequest;
import com.shooza.demo.DTO.PromoCodeResponse;
import com.shooza.demo.exceptions.InvalidPromoCodeException;
import com.shooza.demo.models.CodePromo;
import com.shooza.demo.repositories.PromoCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodePromoService {

    @Autowired
    private PromoCodeRepository promoCodeRepository;

    public ResponseEntity<?> getPromos(){
            return ResponseEntity.ok(promoCodeRepository.findAll());
    }

    public ResponseEntity<?> addPromo(CodePromo codepromo){
            return ResponseEntity.ok(promoCodeRepository.save(codepromo));
    }

    public void deletePromos(List<Integer> ids){
            promoCodeRepository.deleteAllById(ids);
    }

    public PromoCodeResponse validatePromoCode(PromoCodeRequest request){
        CodePromo codePromo = promoCodeRepository.findByCode(request.getCode())
                .orElseThrow(() -> new InvalidPromoCodeException("Le code promo est invalide."));
        return new PromoCodeResponse(true, "Code promo appliqué avec succès.", codePromo.getPercentage());
    }
}
