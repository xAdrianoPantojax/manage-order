package com.ambev.manager_order.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    private static final String QUEUE_NAME = "orders.queue";

    @Bean
    public Queue orderQueue() {
        return new Queue(QUEUE_NAME, true);
    }
}
