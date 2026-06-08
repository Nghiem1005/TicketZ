package com.example.demo.ticket.dto.request;

import java.math.BigDecimal;

public record CreateTicketRequest(
        String type,
        String description,
        BigDecimal unitPrice,
        Integer totalQuantity,
        Integer quantityAvailable) {

}
