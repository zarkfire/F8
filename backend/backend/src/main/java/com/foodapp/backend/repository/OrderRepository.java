package com.foodapp.backend.repository;

import com.foodapp.backend.model.Order;
import com.foodapp.backend.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 🔥 commandes d’un utilisateur
    List<Order> findByUserId(Long userId);

    // 🔥 commandes d’un restaurant
    List<Order> findByRestaurantId(Long restaurantId);

    // 🔥 filtrer par status
    List<Order> findByStatus(OrderStatus status);
}