package com.ambev.manager_order.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItemRequestDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    private String product;
    private double price;
    private int quantity;
}
