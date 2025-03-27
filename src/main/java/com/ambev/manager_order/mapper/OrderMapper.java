package com.ambev.manager_order.mapper;

import com.ambev.manager_order.dto.OrderItemDTO;
import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.dto.OrderResponseDTO;
import com.ambev.manager_order.model.Order;
import com.ambev.manager_order.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "items", target = "items")
    OrderResponseDTO toResponseDTO(Order order);

    @Mapping(source = "items", target = "items")
    Order toEntity(OrderRequestDTO dto);

    List<OrderResponseDTO> toResponseDTOList(List<Order> orders);

    List<OrderItemDTO> orderItemsToDTO(List<OrderItem> items);
}