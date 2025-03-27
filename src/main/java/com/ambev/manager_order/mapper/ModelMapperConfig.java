package com.ambev.manager_order.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.ambev.manager_order.dto.OrderRequestDTO;
import com.ambev.manager_order.model.Order;

public class ModelMapperConfig {

    public static ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<OrderRequestDTO, Order>() {
            @Override
            protected void configure() {
                skip(destination.getOrderId());
            }
        });
        return modelMapper;
    }
}