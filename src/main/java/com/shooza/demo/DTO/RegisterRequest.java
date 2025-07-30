package com.shooza.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Prenom obligatoire")
    private String firstName;

    @NotBlank(message = "Nom obligatoire")
    private String lastName;

    @Email(message = "Format email invalide")
    @NotBlank(message = "Email obligatoire")
    private String email;

    @NotBlank(message = "Mot de passe obligatoire")
    @Length(min = 3, message = "Il faut un minimum de 3 caractères pour votre mot de passe.")
    private String password;

    public RegisterRequest() {}

    public RegisterRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
