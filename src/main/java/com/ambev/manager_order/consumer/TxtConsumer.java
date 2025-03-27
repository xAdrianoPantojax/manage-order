package com.ambev.manager_order.consumer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.service.OrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TxtConsumer {

    private final OrderService orderService;
    private static final String FILE_PATH = "orders.txt";

    @Scheduled(fixedRate = 60000) // Executa a cada 60 segundos
    public void readOrdersFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                processOrder(line);
            }
            Files.write(Paths.get(FILE_PATH), new byte[0]); // Limpa o arquivo após processamento
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private void processOrder(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 3) {
            System.err.println("Linha inválida: " + line);
            return;
        }

        OrderRequestDTO order = new OrderRequestDTO();
        order.setCustomer(parts[0]);
        order.setTotalAmount(Double.parseDouble(parts[1]));
        order.setStatus(parts[2]);

        orderService.createOrder(order);
        System.out.println("Pedido processado: " + order);
    }
}
