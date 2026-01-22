package com.example.email_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.example.email_service.config.ConsumerConfig;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailConsumerService {

    @RabbitListener(queues = ConsumerConfig.QUEUE_NAME)
    public void consumeMessage(String message) {
        System.out.println("Messagem recebida: " + message);
    }
}
