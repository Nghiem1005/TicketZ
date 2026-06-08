package com.example.demo.event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.event.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(String name);
}
