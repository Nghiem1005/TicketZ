package com.example.demo.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.utils.ApiResponse;
import com.example.demo.order.dto.request.CreateOrderRequest;
import com.example.demo.order.dto.response.OrderReponse;
import com.example.demo.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderCotronller {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderReponse>> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        OrderReponse orderResponse = orderService.purchase(request);
        ApiResponse<OrderReponse> response = new ApiResponse<>(true, "Order created successfully", orderResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
