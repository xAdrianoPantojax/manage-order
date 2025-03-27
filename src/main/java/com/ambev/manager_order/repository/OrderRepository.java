package com.ambev.manager_order.repository;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = {"items"})
    Optional<Order> findByOrderId(Long orderId);
   // boolean existsByCustomerAndTotalAmountAndStatus(OrderRequestDTO orderRequestDTO);

}