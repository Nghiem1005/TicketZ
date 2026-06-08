package com.example.demo.event.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record CreateEventRequest(
                @NotBlank String name,

                String description,

                @NotBlank String location,

                @NotBlank LocalDateTime startTime,

                @NotBlank LocalDateTime endTime) {
}