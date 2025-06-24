package com.shooza.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CodePromo {
    @Id
    @GeneratedValue
    private int id;
    private String code;
    private int percentage;
}
