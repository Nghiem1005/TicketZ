package com.example.demo.event.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.utils.ApiResponse;
import com.example.demo.event.dto.request.CreateEventRequest;
import com.example.demo.event.dto.response.EventResponse;
import com.example.demo.event.service.EventService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<ApiResponse<EventResponse>> createEvent(@Valid @RequestBody CreateEventRequest request) {
        EventResponse eventResponse = eventService.createEvent(request);
        ApiResponse<EventResponse> response = new ApiResponse<>(true, "Event created successfully", eventResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponse>> getEventById(@PathVariable Long id) {
        EventResponse eventResponse = eventService.getEventById(id);
        ApiResponse<EventResponse> response = new ApiResponse<>(true, "Event retrieved successfully", eventResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<EventResponse>>> getAllEvents() {
        List<EventResponse> events = eventService.getAllEvents();
        ApiResponse<List<EventResponse>> response = new ApiResponse<>(true, "Events retrieved successfully", events);
        return ResponseEntity.ok(response);
    }
}
