package com.shooza.demo.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AuthRequest {

    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
