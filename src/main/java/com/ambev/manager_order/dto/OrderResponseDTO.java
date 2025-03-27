package com.ambev.manager_order.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private String customer;
    private double totalAmount;
    private String status;
    private List<OrderItemDTO> items;
}
