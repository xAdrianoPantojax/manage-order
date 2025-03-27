package com.ambev.manager_order.service;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.mapper.OrderMapper;
import com.ambev.manager_order.model.Order;
import com.ambev.manager_order.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileOrderConsumer {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ObjectMapper objectMapper;

    private static final String FILE_PATH = "files/orders.txt"; // Arquivo local

    public void processOrdersFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

            for (String line : lines) {
                try {
                    OrderRequestDTO orderRequest = objectMapper.readValue(line, OrderRequestDTO.class);
                    Order order = orderMapper.toEntity(orderRequest);
                    orderRepository.save(order);
                    System.out.println("✅ Pedido salvo do arquivo: " + order);
                } catch (Exception e) {
                    System.err.println("❌ Erro ao processar pedido do arquivo: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
