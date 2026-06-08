package com.example.demo.event.dto.response;

public record EventResponse(
                String name,
                String description,
                String location,
                String startTime,
                String endTime) {
}
