package com.ambev.manager_order.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private String product;
    private int quantity;
    private double price;
}
