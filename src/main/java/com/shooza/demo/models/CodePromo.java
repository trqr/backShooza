package com.shooza.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CodePromo {
    @Id
    private int id;
    @NotNull(message = "ne peut pas être null")
    private String code;
    @NotNull(message = "ne peut pas être null")
    @Min(value = 0, message = "Le pourcentage du code promo ne peux pas être inférieur à 0")
    @Max(value = 100, message = "Le pourcentage du code promo ne peux pas être supérieur à 100")
    private int percentage;

    public CodePromo() {
    }


    public CodePromo(String code, int percentage) {
        this.code = code;
        this.percentage = percentage;
    }
}
