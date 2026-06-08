package com.example.demo.event.mapper;

import org.mapstruct.Mapper;

import com.example.demo.event.dto.request.CreateEventRequest;
import com.example.demo.event.dto.response.EventResponse;
import com.example.demo.event.entity.Event;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(CreateEventRequest request);

    EventResponse toResponse(Event event);
}
