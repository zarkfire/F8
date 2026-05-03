package com.foodapp.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequest {

    private Long restaurantId;
    private List<OrderItemRequest> items;

}