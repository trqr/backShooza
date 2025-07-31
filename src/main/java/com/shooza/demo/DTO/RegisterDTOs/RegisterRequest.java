package com.shooza.demo.DTO.RegisterDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "First name is needed")
    private String firstName;

    @NotBlank(message = "Last Name is needed")
    private String lastName;

    @Email(message = "Email format invalid")
    @NotBlank(message = "Email is needed")
    private String email;

    @NotBlank(message = "Password is needed")
    @Length(min = 3, message = "Your password must be at least 3 characters long.")
    private String password;

    public RegisterRequest() {}

    public RegisterRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
