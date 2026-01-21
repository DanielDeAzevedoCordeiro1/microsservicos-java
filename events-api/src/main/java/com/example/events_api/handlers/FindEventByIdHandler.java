package com.example.events_api.handlers;

import org.springframework.stereotype.Service;
import java.util.UUID;
import com.example.events_api.domain.Event;
import com.example.events_api.repositories.EventRepository;

@Service
public class FindEventByIdHandler {
    
    private final EventRepository eventRepository;

    public FindEventByIdHandler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event handle(String eventId) {
        return eventRepository.findByEventId(UUID.fromString(eventId))
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
    }
}
