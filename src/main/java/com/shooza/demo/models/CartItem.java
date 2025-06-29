package com.shooza.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private double productPrice;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
