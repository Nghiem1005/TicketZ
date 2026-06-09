package com.example.demo.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.common.event.TicketReservedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaTestRunner implements CommandLineRunner {

    private final TicketEventProducer producer;

    @Override
    public void run(String... args) {

        producer.publish(
                new TicketReservedEvent(
                        1L,
                        1L,
                        2));
    }
}