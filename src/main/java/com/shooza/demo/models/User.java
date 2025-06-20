package com.shooza.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
