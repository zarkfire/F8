package com.foodapp.backend.controller;

import com.foodapp.backend.model.Restaurant;
import com.foodapp.backend.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;
import com.foodapp.backend.model.Menu;
import com.foodapp.backend.repository.MenuRepository;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final MenuRepository menuRepository;
    private final RestaurantRepository repository;

    public RestaurantController(MenuRepository menuRepository, RestaurantRepository repository) {
        this.menuRepository = menuRepository;
        this.repository = repository;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return repository.save(restaurant);
    }
    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }
    @GetMapping("/{id}/menus")
    public List<Menu> getMenusByRestaurant(@PathVariable Long id) {
        return menuRepository.findByRestaurantId(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}