package com.example.demo.ticket.entity;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.common.utils.BaseEntity;
import com.example.demo.event.entity.Event;
import com.example.demo.order.entity.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {
    @Column(nullable = false)
    private String type;

    @Column(length = 255)
    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer quantityAvailable;

    @Column(nullable = false)
    private Integer totalQuantity;

    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;
}
