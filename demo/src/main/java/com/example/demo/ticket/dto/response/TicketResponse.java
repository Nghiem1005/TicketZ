package com.example.demo.ticket.dto.response;

public record TicketResponse(
        String type,
        String description,
        String unitPrice,
        Integer totalQuantity,
        Integer quantityAvailable) {

}
