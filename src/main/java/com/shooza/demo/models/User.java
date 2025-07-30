package com.shooza.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

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

    private String role = "USER";

    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
