package com.foodapp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private double price; // prix figé au moment de la commande

    @ManyToOne
    private Menu menu;

    @ManyToOne
    @JsonIgnore
    private Order order;

    // ======================
    // GETTERS & SETTERS
    // ======================

}