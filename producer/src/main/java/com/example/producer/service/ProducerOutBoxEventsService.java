package com.example.producer.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.producer.config.ProducerConfig;
import com.example.producer.domain.EventPayload;
import com.example.producer.repositories.OutBoxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProducerOutBoxEventsService {

    Logger logger = Logger.getLogger(ProducerOutBoxEventsService.class.getName());

    private final OutBoxRepository outBoxRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public ProducerOutBoxEventsService(OutBoxRepository outBoxRepository,
                                       RabbitTemplate rabbitTemplate,
                                       ObjectMapper objectMapper) {
        this.outBoxRepository = outBoxRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    @Transactional
    @Scheduled(fixedDelay = 5000)
    public void processOutBoxEvents() throws JsonProcessingException{
        var outboxEvents = outBoxRepository.findPendingEvents();
        var processedEvents = new HashMap<String, String>();

        for (var outboxEvent : outboxEvents) {
            EventPayload eventPayload = objectMapper.readValue(
                    outboxEvent.getPayload(), 
                    EventPayload.class
                );
            
                
                var sended = send(outboxEvent.getPayload());

                if(sended) {
                    var updatedOutBoxEvent = outBoxRepository.findById(outboxEvent.getId());

                    if(updatedOutBoxEvent.isEmpty()) {
                        throw new RuntimeException("OutBox event not found with id: " + outboxEvent.getId());
                    }

                    var outboxEventToUpdate = updatedOutBoxEvent.get();
                    outboxEventToUpdate.setStatus("SENT");
                    outBoxRepository.save(outboxEventToUpdate);
                }

                processedEvents.put(eventPayload.eventId(), eventPayload.nome());
                
        }
    }


    public boolean send(String payload) {
        rabbitTemplate.convertAndSend(
            ProducerConfig.EXCHANGE_NAME,
            ProducerConfig.ROUTING_KEY,
            payload
        );
        return true;
    }
}

