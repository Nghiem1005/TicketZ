package com.example.demo.order.service;

import java.math.BigDecimal;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.example.demo.common.event.TicketReservedEvent;
import com.example.demo.common.exception.ResourceNotFoundException;
import com.example.demo.common.exception.TicketSoldOutException;
import com.example.demo.order.dto.response.OrderReponse;
import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.OrderStatus;
import com.example.demo.order.mapper.OrderMapper;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.ticket.entity.Ticket;
import com.example.demo.ticket.repository.TicketRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderReponse purchase(TicketReservedEvent request) {
        Ticket ticket = ticketRepository.findById(request.ticketId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        ticket.setQuantityAvailable(ticket.getQuantityAvailable() - request.quantity());

        BigDecimal totalPrice = ticket.getUnitPrice().multiply(BigDecimal.valueOf(request.quantity()));

        Order order = new Order();
        order.setPrice(totalPrice);

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        order.setUser(user);
        order.setTicket(ticket);
        order.setQuantity(request.quantity());

        order.setStatus(OrderStatus.PENDING);

        try {
            ticketRepository.saveAndFlush(ticket);
            Order savedOrder = orderRepository.save(order);
            OrderReponse response = orderMapper.toResponse(savedOrder);
            return response;
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("OPTIMISTIC LOCK DETECTED");
            throw new TicketSoldOutException("Ticket sold out during purchase process");
        }

    }

}
