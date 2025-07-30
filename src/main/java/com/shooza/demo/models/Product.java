package com.shooza.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;
    private String brand;
    private double price;
    private String color;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<ProductImage> imagesUrl;

    @Min(value = 0, message = "Le stock ne peut pas être négatif")
    @Column(columnDefinition = "integer default 5")
    private int stock = 5;

    private String status = "visible";

    public Product(String name, String brand, double price, String color, int stock) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.stock = stock;
    }
}
