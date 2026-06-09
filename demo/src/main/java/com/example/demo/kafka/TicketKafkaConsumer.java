package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.common.event.TicketReservedEvent;
import com.example.demo.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketKafkaConsumer {
    private final OrderService orderService;

    @KafkaListener(topics = "ticket-reserved", groupId = "order-group")
    public void consume(TicketReservedEvent event) {
        orderService.purchase(event);
        System.out.println("EVENT RECEIVED -> " + event);
    }
}
