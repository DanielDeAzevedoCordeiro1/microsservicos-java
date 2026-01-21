package com.example.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class ProducerConfig {
    
    public static final String EXCHANGE_NAME = "events.exchange";
    public static final String ROUTING_KEY = "events.confirmation.key";
    public static final String QUEUE = "events.email.queue";

    @Bean
    public Queue upQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public DirectExchange upExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding upBinding(Queue upQueue, DirectExchange upExchange) {
        return BindingBuilder.bind(upQueue)
            .to(upExchange)
            .with(ROUTING_KEY);
    }


    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
