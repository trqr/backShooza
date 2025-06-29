package com.shooza.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="commande")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    private CodePromo codepromo;
    private int delivery;
    private String status;
    @OneToMany
    private List<CartItem> items;
}
