package com.example.events_api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.events_api.domain.Event;
import com.example.events_api.domain.dtos.EventDTO;
import com.example.events_api.handlers.FindAllEventsHandler;
import com.example.events_api.handlers.FindEventByIdHandler;


@RestController
@RequestMapping("/api/events")
public class FindEventController{
    
    private final FindEventByIdHandler findEventByIdHandler;
    private final FindAllEventsHandler findAllEventsHandler;

    public FindEventController(FindEventByIdHandler findEventByIdHandler, 
                               FindAllEventsHandler findAllEventsHandler) {
        this.findEventByIdHandler = findEventByIdHandler;
        this.findAllEventsHandler = findAllEventsHandler;
    }

    @GetMapping("/find/{eventId}")
    public ResponseEntity<Event> findEvent(@PathVariable String eventId) {
        var event = findEventByIdHandler.handle(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<EventDTO>> findAllEvents() {
        var events = findAllEventsHandler.handle();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    
}
