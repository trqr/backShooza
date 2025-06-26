package com.shooza.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoCodeResponse {
    private boolean success;
    private String message;
    private int value;

    public PromoCodeResponse(boolean success, String message, int value) {
        this.success = success;
        this.message = message;
        this.value = value;
    }

}
