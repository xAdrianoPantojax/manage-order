package com.ambev.manager_order.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.ambev.manager_order.dto.OrderRequestDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueueProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendOrder(OrderRequestDTO order) {
        rabbitTemplate.convertAndSend("orderQueue", order);
        System.out.println("Pedido enviado para a fila: " + order);
    }
}
