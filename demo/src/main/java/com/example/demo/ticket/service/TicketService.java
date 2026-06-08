package com.example.demo.ticket.service;

import java.util.List;

import com.example.demo.ticket.dto.request.CreateTicketRequest;
import com.example.demo.ticket.dto.response.TicketResponse;

public interface TicketService {
    TicketResponse createTicket(Long eventId, CreateTicketRequest request);

    List<TicketResponse> getAllTickets();
}
