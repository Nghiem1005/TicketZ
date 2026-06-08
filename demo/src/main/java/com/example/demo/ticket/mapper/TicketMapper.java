package com.example.demo.ticket.mapper;

import org.mapstruct.Mapper;

import com.example.demo.ticket.dto.request.CreateTicketRequest;
import com.example.demo.ticket.dto.response.TicketResponse;
import com.example.demo.ticket.entity.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toEntity(CreateTicketRequest request);

    TicketResponse toResponse(Ticket ticket);
}
