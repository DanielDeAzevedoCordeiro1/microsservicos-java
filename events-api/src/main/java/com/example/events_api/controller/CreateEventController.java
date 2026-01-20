package com.example.events_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.events_api.domain.Event;
import com.example.events_api.domain.dtos.EventDTO;
import com.example.events_api.handlers.CreateEventHandler;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/events")
public class CreateEventController {
    
    private final CreateEventHandler createEventHandler;

    public CreateEventController(CreateEventHandler createEventHandler) {
        this.createEventHandler = createEventHandler;
    }

    @PostMapping("/create")
    public Event createEvent(@RequestBody EventDTO event) throws JsonProcessingException {
        return createEventHandler.handle(event);
    }
    
}
