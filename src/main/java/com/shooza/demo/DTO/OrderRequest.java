package com.shooza.demo.DTO;

import com.shooza.demo.models.CartItem;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderRequest {

    @NotNull(message = "ne peut pas être null")
    @Email(message = "Le format requis est email")
    private String userEmail;
    private String promoCode;
    @NotNull
    private double deliveryValue;
    @NotNull(message = "ne peut pas être null")
    @Min(0)
    private double totalPrice;
    private List<CartItem> cart;

    public OrderRequest(String userEmail, String promoCode, double deliveryValue, double totalPrice, List<CartItem> cart) {
        this.userEmail = userEmail;
        this.promoCode = promoCode;
        this.deliveryValue = deliveryValue;
        this.totalPrice = totalPrice;
        this.cart = cart;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public double getDeliveryValue() {
        return deliveryValue;
    }

    public void setDeliveryValue(double deliveryValue) {
        this.deliveryValue = deliveryValue;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }
}