package com.shooza.demo.DTO;

import com.shooza.demo.models.Order;
import lombok.Data;

@Data
public class OrderResponse {

    private boolean success;
    private double orderPrice;
    private String orderStatus;
    private String message;

    public OrderResponse(boolean success, double orderPrice, String orderStatus, String message){
        this.success = success;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.message = message;
    }
}
