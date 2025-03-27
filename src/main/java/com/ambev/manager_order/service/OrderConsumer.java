package com.ambev.manager_order.service;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.mapper.OrderMapper;
import com.ambev.manager_order.model.Order;
import com.ambev.manager_order.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "orders.queue")
    public void receiveMessage(String message) {
        try {
            OrderRequestDTO orderRequest = objectMapper.readValue(message, OrderRequestDTO.class);
            Order order = orderMapper.toEntity(orderRequest);

            orderRepository.save(order);
            System.out.println("✅ Pedido salvo com sucesso: " + order);
        } catch (Exception e) {
            System.err.println("❌ Erro ao processar mensagem: " + e.getMessage());
        }
    }
}
