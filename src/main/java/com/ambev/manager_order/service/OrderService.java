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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderResponseConverter orderResponseConverter;
    private final OrderMapper orderMapper; 

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {

        Order order = orderMapper.toEntity(orderRequestDTO);

        // if (isDuplicateOrder(orderRequestDTO)) {
        //     throw new IllegalArgumentException("Já existe um pedido com esses dados.");
        // }

        double total = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setTotalAmount(total);
        order.setStatus("Processed");
        order.getItems().forEach(item -> item.setOrder(order));
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponseDTO(savedOrder);
    }

    // private boolean isDuplicateOrder(OrderRequestDTO orderRequestDTO) {
    //     return orderRepository.existsByCustomerAndTotalAmountAndStatus(orderRequestDTO);
    // }
    
    public List<OrderResponseDTO> processOrdersFromFile() {
    List<Order> orders = orderRepository.findAll(); // Ou carregados do arquivo
    return orders.stream()
                 .map(orderMapper::toResponseDTO)
                 .collect(Collectors.toList());
}

    public OrderResponseDTO getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(orderResponseConverter::toDto).orElse(null);
    }

}
