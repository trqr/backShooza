package com.shooza.demo.controllers;

import com.shooza.demo.DTO.OrderRequest;
import com.shooza.demo.DTO.OrderResponse;
import com.shooza.demo.DTO.StatusUpdateRequest;
import com.shooza.demo.models.CartItem;
import com.shooza.demo.models.CodePromo;
import com.shooza.demo.models.Order;
import com.shooza.demo.repositories.CartItemRepository;
import com.shooza.demo.repositories.OrderRepository;
import com.shooza.demo.repositories.PromoCodeRepository;
import com.shooza.demo.repositories.UserRepository;
import com.shooza.demo.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    @PostMapping("/order/create-order")
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

        for (CartItem item : cartItems) {
            if (item.getProduct().getStock() < item.getQuantity()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le produit " + item.getProduct().getName() + " n'est pas disponible en stock");
            }
            item.setOrder(newOrder);
            item.getProduct().setStock(item.getProduct().getStock() - item.getQuantity());
        }

        newOrder.setCart(cartItems);
        orderRepository.save(newOrder);

        return ResponseEntity.ok(new OrderResponse(true, newOrder.getTotalPrice(), newOrder.getStatus(), "Commande enregistrée") {
        });
    }

    @PostMapping("/order/delete")
    public void deleteOrders(@RequestBody List<Integer> ids) {
        orderRepository.deleteAllById(ids);
    }

    @PostMapping("/order/status")
    public void changeOrderStatus(@RequestBody StatusUpdateRequest request) {
        List<Order> orders = orderRepository.findAllById(request.getIds());
        for (Order order : orders) {
            order.setStatus(request.getNewStatus());
        }
        orderRepository.saveAll(orders);
    }

    @GetMapping("order/user")
    public ResponseEntity<?> getUserOrders(        @RequestHeader("Authorization") String authHeader,
                                             @RequestParam("userId") int userId) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token manquant ou invalide");
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);

        if (!role.equals("USER") && !role.equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès interdit");
        }

        return ResponseEntity.ok(orderRepository.findOrdersWithoutUser(userId));
    }

    @GetMapping("order/all")
    public ResponseEntity<?> getOrders(@RequestHeader("Authorization") String authHeader){

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token manquant ou invalide");
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);

        if (!role.equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès interdit");
        }

        return ResponseEntity.ok(orderRepository.findAll());
    }
}
