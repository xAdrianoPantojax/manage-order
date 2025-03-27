package com.ambev.manager_order.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.service.OrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueueConsumer {

    private final OrderService orderService;

    @RabbitListener(queues = "orderQueue")
    public void receiveOrder(OrderRequestDTO orderRequestDTO) {
        orderService.createOrder(orderRequestDTO);
        System.out.println("Pedido recebido da fila: " + orderRequestDTO);
    }
}
