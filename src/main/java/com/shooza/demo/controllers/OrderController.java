package com.shooza.demo.controllers;

import com.shooza.demo.DTO.OrderRequest;
import com.shooza.demo.DTO.OrderResponse;
import com.shooza.demo.models.CartItem;
import com.shooza.demo.models.CodePromo;
import com.shooza.demo.models.Order;
import com.shooza.demo.repositories.CartItemRepository;
import com.shooza.demo.repositories.OrderRepository;
import com.shooza.demo.repositories.PromoCodeRepository;
import com.shooza.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        Optional<CodePromo> optionalCodePromo = promoCodeRepository.findByCode(orderRequest.getPromoCode());
        CodePromo codePromo;
        codePromo = optionalCodePromo.orElse(null);

        Order newOrder = new Order(
                userRepository.findByEmail(orderRequest.getUserEmail()),
                codePromo,
                orderRequest.getDeliveryValue(),
                orderRequest.getTotalPrice(),
                null
        );

        List<CartItem> cartItems = orderRequest.getCart();
        cartItems.forEach(item -> item.setOrder(newOrder));
        newOrder.setCart(cartItems);
        orderRepository.save(newOrder);

        return ResponseEntity.ok(new OrderResponse(true, newOrder.getTotalPrice(), newOrder.getStatus(), "Commande enregistrée") {
        });
    }

    @GetMapping("")
    public List<Order> getUserOrders(@RequestParam("userId") int userId) {
        return orderRepository.findByUserId(userId);
    }
}
