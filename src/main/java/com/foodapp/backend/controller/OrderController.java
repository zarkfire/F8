package com.foodapp.backend.controller;

import com.foodapp.backend.dto.OrderItemRequest;
import com.foodapp.backend.dto.OrderRequest;
import com.foodapp.backend.model.*;
import com.foodapp.backend.repository.MenuRepository;
import com.foodapp.backend.repository.OrderRepository;
import com.foodapp.backend.repository.RestaurantRepository;
import com.foodapp.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public OrderController(
            OrderRepository orderRepository,
            MenuRepository menuRepository,
            RestaurantRepository restaurantRepository,
            UserRepository userRepository
    ) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    // 📥 GET all orders
    @GetMapping
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    // 📥 GET one order
    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    @PutMapping("/{id}/pay")
    public Order pay(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.PAID);
        return orderRepository.save(order);
    }

    @PutMapping("/{id}/prepare")
    public Order prepare(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.PREPARING);
        return orderRepository.save(order);
    }

    @PutMapping("/{id}/ready")
    public Order ready(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.READY);
        return orderRepository.save(order);
    }

    @PutMapping("/{id}/deliver")
    public Order deliver(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }

    // ➕ CREATE ORDER PRO (avec panier)
    @PostMapping
    public Order create(@RequestBody OrderRequest request) {

        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow();

        order.setRestaurant(restaurant);

        // 👉 ici tu peux brancher JWT plus tard
        // order.setUser(currentUser);

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (OrderItemRequest itemReq : request.getItems()) {

            Menu menu = menuRepository.findById(itemReq.getMenuId())
                    .orElseThrow();

            OrderItem item = new OrderItem();
            item.setMenu(menu);
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(menu.getPrice());
            item.setOrder(order);

            total += menu.getPrice() * itemReq.getQuantity();
            items.add(item);
        }

        order.setItems(items);
        order.setTotalPrice(total);

        return orderRepository.save(order);
    }

    // 💳 PAY ORDER (upgrade simple)
    @PostMapping("/{id}/pay")
    public Order pay2(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(OrderStatus.PAID);
        return orderRepository.save(order);
    }

    // 🗑 DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}