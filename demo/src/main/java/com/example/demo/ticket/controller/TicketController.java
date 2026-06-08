package com.example.demo.ticket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.utils.ApiResponse;
import com.example.demo.ticket.dto.request.CreateTicketRequest;
import com.example.demo.ticket.dto.response.TicketResponse;
import com.example.demo.ticket.service.TicketService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/{eventId}")
    public ResponseEntity<ApiResponse<TicketResponse>> createTicket(@PathVariable Long eventId,
            @Valid @RequestBody CreateTicketRequest request) {
        TicketResponse ticketResponse = ticketService.createTicket(eventId, request);
        ApiResponse<TicketResponse> response = new ApiResponse<>(true, "Ticket created successfully", ticketResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TicketResponse>>> getAllTickets() {
        List<TicketResponse> ticketResponses = ticketService.getAllTickets();
        ApiResponse<List<TicketResponse>> response = new ApiResponse<>(true, "Tickets retrieved successfully",
                ticketResponses);
        return ResponseEntity.ok(response);
    }

}
