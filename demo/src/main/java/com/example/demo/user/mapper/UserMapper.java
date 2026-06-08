package com.example.demo.user.mapper;

import org.mapstruct.Mapper;

import com.example.demo.user.dto.request.CreateUserRequest;
import com.example.demo.user.dto.response.UserResponse;
import com.example.demo.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(CreateUserRequest request);

    UserResponse toResponse(User user);
}
