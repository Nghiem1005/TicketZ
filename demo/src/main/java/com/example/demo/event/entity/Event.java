package com.example.demo.event.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.common.utils.BaseEntity;
import com.example.demo.ticket.entity.Ticket;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}