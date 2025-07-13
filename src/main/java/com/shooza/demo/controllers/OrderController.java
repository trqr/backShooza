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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        cartItems.forEach(item -> item.setOrder(newOrder));
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
    public List<Order> getUserOrders(@RequestParam("userId") int userId) {
        return orderRepository.findOrdersWithoutUser(userId);
    }

    @GetMapping("order/all")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
}
