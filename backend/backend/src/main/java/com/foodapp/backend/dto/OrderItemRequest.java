package com.foodapp.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemRequest {

    private Long menuId;
    private int quantity;

}