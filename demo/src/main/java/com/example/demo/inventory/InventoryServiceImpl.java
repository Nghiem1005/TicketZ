package com.example.demo.inventory;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import com.example.demo.config.RedisLuaConfig;
import com.example.demo.ticket.entity.Ticket;
import com.example.demo.ticket.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final TicketRepository ticketRepository;
    private final StringRedisTemplate redisTemplate;
    private final RedisScript<Long> deductInventoryScript;

    @EventListener(ApplicationReadyEvent.class)
    public void preloadInventory() {

        List<Ticket> tickets = ticketRepository.findAll();

        tickets.forEach(ticket -> {

            String key = "ticket:"
                    + ticket.getId()
                    + ":inventory";

            redisTemplate.opsForValue().set(
                    key,
                    String.valueOf(
                            ticket.getQuantityAvailable()));

        });
    }

    @Override
    public Integer getAvailableQuantity(Long ticketId) {
        return Integer.valueOf(redisTemplate.opsForValue().get("ticket:" + ticketId + ":inventory"));
    }

    @Override
    public boolean deductInventory(Long ticketId, int quantity) {

        String key = "ticket:" + ticketId + ":inventory";
        Long result = redisTemplate.execute(
                deductInventoryScript,
                Collections.singletonList(key),
                String.valueOf(quantity));

        return result != null && result == 1L;
    }
}
