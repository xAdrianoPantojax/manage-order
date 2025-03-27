package com.ambev.manager_order.service;


import com.ambev.manager_order.converter.OrderResponseConverter;
import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.dto.OrderResponseDTO;
import com.ambev.manager_order.mapper.OrderMapper;
import com.ambev.manager_order.model.Order;
import com.ambev.manager_order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderResponseConverter orderResponseConverter;
    private final OrderMapper orderMapper; 

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {

        Order order = orderMapper.toEntity(orderRequestDTO);

        double total = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setTotalAmount(total);
        order.setStatus("Processed");
        order.getItems().forEach(item -> item.setOrder(order));
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponseDTO(savedOrder);
    }

    public OrderResponseDTO getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(orderResponseConverter::toDto).orElse(null);
    }

}
