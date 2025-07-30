package com.shooza.demo.exceptions;

import com.shooza.demo.DTO.PromoCodeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPromoCodeException.class)
    public ResponseEntity<PromoCodeResponse> handleInvalidPromoCode(InvalidPromoCodeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PromoCodeResponse(false, ex.getMessage(), 0));
    }
}
