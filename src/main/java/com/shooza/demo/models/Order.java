package com.shooza.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

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
    private User user;

    @ManyToOne
    private CodePromo codepromo;

    private double delivery;
    private double totalPrice;
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartItem> items;

    public Order(User user, CodePromo codepromo, double delivery, double totalPrice, List<CartItem> items) {
        this.user = user;
        this.codepromo = codepromo;
        this.delivery = delivery;
        this.totalPrice = totalPrice;
        this.status = "pending";
        this.items = items;
    }

    public Order(User user, double delivery, double totalPrice, List<CartItem> items) {
        this.user = user;
        this.codepromo = codepromo;
        this.delivery = delivery;
        this.totalPrice = totalPrice;
        this.status = "pending";
        this.items = items;
    }
}
