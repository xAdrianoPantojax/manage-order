
package com.ambev.manager_order.converter;
import org.springframework.stereotype.Component;

import com.ambev.manager_order.dto.OrderResponseDTO;
import com.ambev.manager_order.mapper.BaseConverter;
import com.ambev.manager_order.model.Order;

@Component
public class OrderResponseConverter implements BaseConverter<Order, OrderResponseDTO> {
}
