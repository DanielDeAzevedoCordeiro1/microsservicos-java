package com.example.events_api.domain.dtos;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private String nome;
    private String email;
    private String cnpj;
    private String empresa;
    private String local;
    private String responsavel;
    private Instant data;

}
