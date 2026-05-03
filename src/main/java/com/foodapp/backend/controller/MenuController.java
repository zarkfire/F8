package com.foodapp.backend.controller;

import com.foodapp.backend.model.Menu;
import com.foodapp.backend.repository.MenuRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    private final MenuRepository repository;

    public MenuController(MenuRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Menu> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Menu create(@RequestBody Menu menu) {
        return repository.save(menu);
    }
}