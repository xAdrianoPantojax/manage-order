package com.ambev.manager_order.controller;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.dto.OrderResponseDTO;
import com.ambev.manager_order.service.FileOrderConsumer;
import com.ambev.manager_order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final FileOrderConsumer fileOrderConsumer;

    @Operation(summary = "Processa pedidos do arquivo orders.txt")
    @GetMapping("/process-file-orders")
    public ResponseEntity<List<OrderResponseDTO>> processFileOrders() {
        List<OrderResponseDTO> processedOrders = orderService.processOrdersFromFile();
        return ResponseEntity.ok(processedOrders);
}

    @Operation(summary = "Create new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order Created", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = OrderRequestDTO.class), 
            examples = @ExampleObject(value = """
                    {
                        "orderId": 8,
                        "customer": "João Pereira",
                        "totalAmount": 4850.75,
                        "status": "Processed",
                        "items": [
                            {
                                "product": "Smartphone Samsung Galaxy S23",
                                "quantity": 1,
                                "price": 4200.0
                            },
                            {
                                "product": "Carregador Turbo Samsung",
                                "quantity": 1,
                                "price": 250.75
                            },
                            {
                                "product": "Fone de Ouvido Bluetooth JBL",
                                "quantity": 1,
                                "price": 400.0
                            }
                        ]
                    }
                                                """))),
            @ApiResponse(responseCode = "400", 
            description = "Invalid request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Data of the order to be created", 
            required = true, 
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = OrderResponseDTO.class), 
            examples = @ExampleObject(value = """
                    {
                       "customer": "João Pereira",
                       "totalAmount": 4850.75,
                       "status": "CONFIRMADO",
                       "items": [
                           {
                               "product": "Smartphone Samsung Galaxy S23",
                               "price": 4200.00,
                               "quantity": 1
                           },
                           {
                               "product": "Carregador Turbo Samsung",
                               "price": 250.75,
                               "quantity": 1
                           },
                       ]
                    }
                """)
                )
            ) 
            @Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO createdOrder = orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @Operation(summary = "Returns an order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", 
            description = "Order found", 
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = OrderRequestDTO.class), 
            examples = @ExampleObject(value = """
                    {
                        "orderId": 1,
                        "customer": "João Pereira",
                        "totalAmount": 4850.75,
                        "status": "Processed",
                        "items": [
                            {
                                "product": "Smartphone Samsung Galaxy S23",
                                "quantity": 1,
                                "price": 4200.0
                            },
                            {
                                "product": "Carregador Turbo Samsung",
                                "quantity": 1,
                                "price": 250.75
                            },
                        ]
                    }
                """))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        OrderResponseDTO order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
