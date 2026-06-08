package com.example.demo.common.utils;

public record ApiResponse<T>(
        boolean status,
        String message,
        T data) {
}
