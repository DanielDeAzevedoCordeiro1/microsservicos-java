package com.example.email_service.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {
    public static final String QUEUE_NAME = "events.email.queue";
}
