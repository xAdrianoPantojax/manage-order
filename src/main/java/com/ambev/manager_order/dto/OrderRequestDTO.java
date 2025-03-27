package com.ambev.manager_order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private String customer;
    private double totalAmount;
    private String status;
    private List<OrderItemRequestDTO> items;
}
