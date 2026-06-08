package com.example.demo.order.dto.request;

public record CreateOrderRequest(
        Long userId,
        Long ticketId,
        Integer quantity) {

}
