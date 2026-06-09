package com.example.demo.order;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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
        System.out.println("TEST START");
        int numberOfUsers = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(numberOfUsers);

        AtomicInteger success = new AtomicInteger();

        AtomicInteger fail = new AtomicInteger();

        for (int i = 0; i < numberOfUsers; i++) {

            executorService.submit(() -> {

                try {

                    CreateOrderRequest request = new CreateOrderRequest(
                            1L,
                            1L,
                            1);

                    orderService.purchase(request);
                    success.incrementAndGet();

                } catch (Exception ignored) {
                    fail.incrementAndGet();
                }

                latch.countDown();

            });
        }

        latch.await();

        // Kiểm tra kết quả cuối cùng

        System.out.println(
                "Success = " + success.get());

        System.out.println(
                "Fail = " + fail.get());
    }
}
