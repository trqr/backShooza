package com.shooza.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class ProductImage {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name= "product_id")
    @JsonBackReference
    private Product product;
    private String imgUrl;
}
