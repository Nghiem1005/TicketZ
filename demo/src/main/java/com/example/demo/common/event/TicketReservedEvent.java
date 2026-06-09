package com.example.demo.common.event;

public record TicketReservedEvent(Long ticketId, Long userId, Integer quantity) {
}
