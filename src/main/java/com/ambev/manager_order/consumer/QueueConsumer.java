package com.ambev.manager_order.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.mapper.OrderMapper;
import com.ambev.manager_order.model.Order;
import com.ambev.manager_order.repository.OrderRepository;
import com.ambev.manager_order.service.OrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueueConsumer {

    // private final OrderService orderService;

    // @RabbitListener(queues = "orderQueue")
    // public void receiveOrder(OrderRequestDTO orderRequestDTO) {
    //     orderService.createOrder(orderRequestDTO);
    //     System.out.println("Pedido recebido da fila: " + orderRequestDTO);
    // }

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    // Este método será chamado quando uma mensagem for recebida da fila
    @RabbitListener(queues = "orderQueue")
    public void receiveOrder(OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.toEntity(orderRequestDTO);
        order.setStatus("Processed");
        orderRepository.save(order);
        System.out.println("✅ Pedido processado e salvo: " + orderRequestDTO);
    }

}
