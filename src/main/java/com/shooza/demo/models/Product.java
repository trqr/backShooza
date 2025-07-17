package com.shooza.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String brand;
    private double price;
    private String color;
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<ProductImage> imagesUrl;
    private int stock;
}
