package com.shooza.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="commande")
public class Order {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    private CodePromo codepromo;

    private double delivery;
    private double totalPrice;
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartItem> cart;

    private LocalDateTime createdAt;

    public Order(User user, CodePromo codepromo, double delivery, double totalPrice, List<CartItem> cart) {
        this.user = user;
        this.codepromo = codepromo;
        this.delivery = delivery;
        this.totalPrice = totalPrice;
        this.status = "pending";
        this.cart = cart;
        this.createdAt = LocalDateTime.now();
    }

    public Order(User user, double delivery, double totalPrice, List<CartItem> cart) {
        this.user = user;
        this.codepromo = codepromo;
        this.delivery = delivery;
        this.totalPrice = totalPrice;
        this.status = "pending";
        this.cart = cart;
    }
}
