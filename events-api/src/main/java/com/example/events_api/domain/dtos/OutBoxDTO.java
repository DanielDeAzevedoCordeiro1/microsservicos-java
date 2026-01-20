package com.example.events_api.domain.dtos;

import java.time.Instant;

public record OutBoxDTO(
    String eventId,
    String nome,
    String email,
    String cnpj,
    String empresa,
    String local,
    String responsavel,
    Instant createdAt,
    Instant data   
) {
    
}
