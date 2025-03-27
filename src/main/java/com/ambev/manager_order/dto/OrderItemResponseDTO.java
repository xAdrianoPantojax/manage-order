package com.ambev.manager_order.dto;

import lombok.Data;

@Data
public class OrderItemResponseDTO {
    private Long id;
    private String product;
    private double price;
    private int quantity;
}
