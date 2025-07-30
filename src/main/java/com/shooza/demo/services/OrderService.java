package com.shooza.demo.services;

import com.shooza.demo.DTO.OrderRequest;
import com.shooza.demo.DTO.OrderResponse;
import com.shooza.demo.DTO.StatusUpdateRequest;
import com.shooza.demo.models.*;
import com.shooza.demo.repositories.OrderRepository;
import com.shooza.demo.repositories.ProductRepository;
import com.shooza.demo.repositories.PromoCodeRepository;
import com.shooza.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getOrders(){
            return orderRepository.findAll();
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        CodePromo codePromo = promoCodeRepository.findByCode(orderRequest.getPromoCode())
                .orElseThrow(IllegalArgumentException::new);

        User buyer = userRepository.findByEmail(orderRequest.getUserEmail());

        List<CartItem> cartItems = orderRequest.getCart();

        Order newOrder = new Order();

        for (CartItem item : cartItems) {
            if (item.getProduct().getStock() < item.getQuantity()) {
                throw new IllegalArgumentException("Le produit " + item.getProduct().getName() + " n'est pas disponible en stock");
            }
            item.setOrder(newOrder);

            Product currentProduct = productRepository.getReferenceById(item.getProduct().getId());
            currentProduct.setStock(currentProduct.getStock() - item.getQuantity());
        }

        newOrder.setUser(buyer);
        newOrder.setCodepromo(codePromo);
        newOrder.setDelivery(orderRequest.getDeliveryValue());
        newOrder.setTotalPrice(orderRequest.getTotalPrice());
        newOrder.setCart(cartItems);

        orderRepository.save(newOrder);

        return new OrderResponse(true, newOrder.getTotalPrice(), newOrder.getStatus(), "Commande enregistrée");
    }

    public List<Order> changeOrderStatus(StatusUpdateRequest request) {
        List<Order> orders = orderRepository.findAllById(request.getIds());
        for (Order order : orders) {
            order.setStatus(request.getNewStatus());
        }
        return orderRepository.saveAll(orders);
    }

    public List<Order> getUserOrders(int userId, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        if (user.getId() != userId) {
            throw new IllegalArgumentException("L'utilisateur n'a pas cet ID");
        }
        return orderRepository.findOrdersWithoutUser(userId);
    }

    private void updateStockItemsAndGetTotalPrice(){}
}
