package com.ambev.manager_order.dto;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private String product;
    private double price;
    private int quantity;
}
