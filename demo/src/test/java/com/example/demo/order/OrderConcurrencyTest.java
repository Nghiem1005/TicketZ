package com.example.demo.order;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.order.dto.request.CreateOrderRequest;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.order.service.OrderService;
import com.example.demo.ticket.entity.Ticket;
import com.example.demo.ticket.repository.TicketRepository;

@SpringBootTest
public class OrderConcurrencyTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void should_show_overselling() throws Exception {

        int numberOfUsers = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(numberOfUsers);

        for (int i = 0; i < numberOfUsers; i++) {

            executorService.submit(() -> {

                try {

                    CreateOrderRequest request = new CreateOrderRequest(
                            1L,
                            1L,
                            1);

                    orderService.purchase(request);

                } catch (Exception ignored) {
                }

                latch.countDown();

            });
        }

        latch.await();

        // Kiểm tra kết quả cuối cùng
        Ticket ticket = ticketRepository.findById(1L)
                .orElseThrow();

        assertNotEquals(
                0,
                ticket.getQuantityAvailable());

        assertTrue(
                orderRepository.count() > 10);
    }
}
