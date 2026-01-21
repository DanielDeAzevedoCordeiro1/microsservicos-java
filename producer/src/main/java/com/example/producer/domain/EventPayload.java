package com.example.producer.domain;

public record EventPayload(
    String eventId,
    String nome,
    String email,
    String cnpj,
    String empresa,
    String local,
    String responsavel,
    String data,
    String createdAt) {
} 
