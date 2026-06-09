package com.example.demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.common.event.TicketReservedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketEventProducer {

    private final KafkaTemplate<String, TicketReservedEvent> kafkaTemplate;

    public void publish(TicketReservedEvent event) {

        kafkaTemplate.send(
                "ticket-reserved",
                event);

        System.out.println(
                "EVENT SENT -> " + event);
    }
}