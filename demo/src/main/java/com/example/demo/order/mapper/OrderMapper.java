package com.example.demo.order.mapper;

import org.mapstruct.Mapper;

import com.example.demo.order.dto.response.OrderReponse;
import com.example.demo.order.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderReponse toResponse(Order order);
}
