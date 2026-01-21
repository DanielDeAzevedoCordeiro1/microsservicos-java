package com.example.events_api.handlers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.events_api.domain.dtos.EventDTO;
import com.example.events_api.repositories.EventRepository;

@Service
public class FindAllEventsHandler {
    
    private final EventRepository eventRepository;

    public FindAllEventsHandler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> handle() {

        var events = eventRepository.findAll();

        if (events.isEmpty()) {
            throw new RuntimeException("No events found.");
        }

        List<EventDTO> eventDTOs = events.stream()
            .map(event -> new EventDTO(
                event.getNome(),
                event.getEmail(),
                event.getCnpj(),
                event.getEmpresa(),
                event.getLocal(),
                event.getResponsavel(),
                event.getData()
            ))
            .toList();

        return eventDTOs;
    }
}
