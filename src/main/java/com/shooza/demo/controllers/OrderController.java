package com.shooza.demo.controllers;

import com.shooza.demo.DTO.OrderRequest;
import com.shooza.demo.DTO.StatusUpdateRequest;
import com.shooza.demo.configuration.AdminOnly;
import com.shooza.demo.models.Order;
import com.shooza.demo.repositories.*;
import com.shooza.demo.services.OrderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @Transactional
    @PostMapping("/order/create-order")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
    }

    @PostMapping("/order/delete")
    @AdminOnly
    public void deleteOrders(@RequestBody List<Integer> ids) {
        orderRepository.deleteAllById(ids);
    }

    @PostMapping("/order/status")
    @AdminOnly
    public ResponseEntity<List<Order>> changeOrderStatus(@Valid @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(orderService.changeOrderStatus(request));
    }

    @GetMapping("order/user")
    public ResponseEntity<List<Order>> getUserOrders(@RequestParam("userId") int userId, Principal principal) {
        return ResponseEntity.ok(orderService.getUserOrders(userId, principal));
    }

    @GetMapping("order/all")
    @AdminOnly
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }
}
