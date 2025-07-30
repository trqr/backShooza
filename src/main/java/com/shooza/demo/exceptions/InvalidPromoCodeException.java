package com.shooza.demo.exceptions;

public class InvalidPromoCodeException extends RuntimeException {
    public InvalidPromoCodeException(String message) {
        super(message);
    }
}
