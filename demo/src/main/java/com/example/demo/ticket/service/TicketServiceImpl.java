package com.example.demo.ticket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.common.exception.ResourceNotFoundException;
import com.example.demo.event.entity.Event;
import com.example.demo.event.repository.EventRepository;
import com.example.demo.ticket.dto.request.CreateTicketRequest;
import com.example.demo.ticket.dto.response.TicketResponse;
import com.example.demo.ticket.entity.Ticket;
import com.example.demo.ticket.mapper.TicketMapper;
import com.example.demo.ticket.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketResponse createTicket(Long eventId, CreateTicketRequest request) {
        Ticket ticket = ticketMapper.toEntity(request);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        ticket.setQuantityAvailable(request.totalQuantity());
        ticket.setEvent(event);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toResponse(ticket);
    }

    @Override
    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticketMapper::toResponse).toList();
    }

}
