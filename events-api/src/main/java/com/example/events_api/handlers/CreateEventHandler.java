package com.example.events_api.handlers;

import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.events_api.domain.Event;
import com.example.events_api.domain.OutBox;
import com.example.events_api.domain.dtos.EventDTO;
import com.example.events_api.domain.enums.EventType;
import com.example.events_api.domain.enums.OutBoxStatus;
import com.example.events_api.repositories.EventRepository;
import com.example.events_api.repositories.OutBoxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreateEventHandler {

    private Logger logger = Logger.getLogger(CreateEventHandler.class.getName());
    
    private final EventRepository eventRepository;
    private final OutBoxRepository outBoxRepository;
    private final ObjectMapper objectMapper;

    public CreateEventHandler(EventRepository eventRepository,
                              OutBoxRepository outBoxRepository,
                              ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.outBoxRepository = outBoxRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Event handle(EventDTO event) throws JsonProcessingException {

        var eventExists = eventRepository.findByEmail(event.getEmail());

        if (eventExists.isPresent()) {
            throw new RuntimeException("Event with email " + event.getEmail() + " already exists.");
        }

        Event newEvent = Event.create(event);

        logger.info("Creating event: " + newEvent.getEventId());
        logger.info("Creating event: " + newEvent.getNome());

        
            Event savedEvent = eventRepository.save(newEvent);



            
            OutBox outboxData = new OutBox();
            outboxData.setEventId(savedEvent.getEventId());
            outboxData.setPayload(objectMapper.writeValueAsString(savedEvent));
            outboxData.setEventType(EventType.EVENT_CREATED.getValue());
            outboxData.setStatus(OutBoxStatus.PENDING.getValue());
            
            outBoxRepository.save(outboxData);
            
            return savedEvent;
       
    }

}
