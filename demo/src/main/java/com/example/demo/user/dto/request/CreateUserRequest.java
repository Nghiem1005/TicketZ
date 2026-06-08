package com.example.demo.user.dto.request;

public record CreateUserRequest(
                String name,
                String email,
                String phone,
                String password) {
}