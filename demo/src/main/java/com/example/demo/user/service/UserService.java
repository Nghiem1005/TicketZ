package com.example.demo.user.service;

import java.util.List;

import com.example.demo.user.dto.request.CreateUserRequest;
import com.example.demo.user.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();
}
