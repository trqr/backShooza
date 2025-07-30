package com.shooza.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String message;


    public RegisterResponse() {
    }

    public RegisterResponse(String firstName, String lastName, String email, String message) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.message = message;
    }
}
