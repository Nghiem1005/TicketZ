package com.example.demo.event.service;

import java.util.List;

import com.example.demo.event.dto.request.CreateEventRequest;
import com.example.demo.event.dto.response.EventResponse;

public interface EventService {
    EventResponse createEvent(CreateEventRequest request);

    EventResponse getEventById(Long id);

    List<EventResponse> getAllEvents();
}
