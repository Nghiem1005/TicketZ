package com.example.demo.order.dto.response;

import java.math.BigDecimal;

import com.example.demo.order.entity.OrderStatus;

public record OrderReponse(
        Long id,
        Long userId,
        Long ticketId,
        Integer quantity,
        BigDecimal totalPrice,
        OrderStatus status) {

}
