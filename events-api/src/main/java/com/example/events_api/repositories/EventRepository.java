package com.example.events_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.example.events_api.domain.Event;
import java.util.List;
import java.util.Optional;


public interface EventRepository extends JpaRepository<Event, UUID> {

    Optional<Event> findByEventId(UUID eventId);
    Optional<Event> findByEmail(String email);
    Optional<List<Event>> findByResponsavel(String responsavel);
    Event save(Event event);

    List<Event> findAll();

} 
