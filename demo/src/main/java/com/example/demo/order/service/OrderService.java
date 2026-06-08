package com.example.demo.order.service;

import com.example.demo.order.dto.request.CreateOrderRequest;
import com.example.demo.order.dto.response.OrderReponse;

import jakarta.transaction.Transactional;

public interface OrderService {
    @Transactional
    OrderReponse purchase(CreateOrderRequest request);
}
