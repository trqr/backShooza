package com.shooza.demo.services;

import com.shooza.demo.repositories.OrderRepository;
import com.shooza.demo.utils.CheckRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CheckRoles checkRoles;

    public ResponseEntity<?> getOrders(String authHeader){
        if (checkRoles.isAdmin(authHeader)) {
            return ResponseEntity.ok(orderRepository.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès interdit");
        }
    }
}
