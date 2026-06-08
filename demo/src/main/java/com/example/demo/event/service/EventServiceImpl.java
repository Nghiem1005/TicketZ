package com.example.demo.event.service;

import org.springframework.stereotype.Service;

import com.example.demo.event.dto.request.CreateEventRequest;
import com.example.demo.event.dto.response.EventResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Override
    public EventResponse createEvent(CreateEventRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEvent'");
    }

    @Override
    public EventResponse getEventById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEventById'");
    }

}
