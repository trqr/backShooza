package com.shooza.demo.DTO.RegisterDTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponse {

    private UserDataResponse data;

    private String message;


    public RegisterResponse() {
    }


    public RegisterResponse(UserDataResponse data, String message) {
        this.data = data;
        this.message = message;
    }
}
