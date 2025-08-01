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

import java.security.Principal;
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
        Order newOrder = new Order();

        CodePromo codePromo = promoCodeRepository.findByCode(orderRequest.getPromoCode())
                .orElseGet(() -> new CodePromo("NONE", 0));

        User buyer = userRepository.findByEmail(orderRequest.getUserEmail());

        List<CartItem> cartItems = orderRequest.getCart();

        double totalPriceWithoutDelivery = updateStockItemsAndGetTotalPrice(cartItems, newOrder);
        double finalPrice = (totalPriceWithoutDelivery*(100 - codePromo.getPercentage())/100) + orderRequest.getDeliveryValue();

        System.out.println(totalPriceWithoutDelivery);
        newOrder.setUser(buyer);
        newOrder.setCodepromo(codePromo);
        newOrder.setDelivery(orderRequest.getDeliveryValue());
        newOrder.setTotalPrice(finalPrice);
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

    public List<Order> getUserOrders(int userId, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()); //getName retourne l'email
        if (user.getId() != userId) {
            throw new IllegalArgumentException("L'utilisateur n'a pas cet ID");
        }
        return orderRepository.findOrdersWithoutUser(userId);
    }

    private double updateStockItemsAndGetTotalPrice(List<CartItem> cartItems, Order newOrder){
        double totalPrice = 0;
        for (CartItem item : cartItems) {
            if (item.getProduct().getStock() < item.getQuantity()) {
                throw new IllegalArgumentException("Le produit " + item.getProduct().getName() + " n'est pas disponible en stock");
            }
            item.setOrder(newOrder);

            totalPrice += (item.getProduct().getPrice() * item.getQuantity());

            Product currentProduct = productRepository.getReferenceById(item.getProduct().getId());
            currentProduct.setStock(currentProduct.getStock() - item.getQuantity());
        }
        return totalPrice;
    }
}
