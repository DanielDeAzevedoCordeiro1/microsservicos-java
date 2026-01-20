package com.example.events_api.domain;

import java.time.Instant;
import java.util.UUID;
import com.example.events_api.domain.dtos.EventDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "events")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID eventId;

    private String nome;
    private String email;
    private String cnpj;
    private String empresa;
    private String local;
    private String responsavel;
    private Instant createdAt;
    private Instant data;

    public static Event create(EventDTO eventDTO) {
        Event event = new Event();
        event.setEventId(UUID.randomUUID());
        event.setNome(eventDTO.getNome());
        event.setEmail(eventDTO.getEmail());
        event.setCnpj(eventDTO.getCnpj());
        event.setEmpresa(eventDTO.getEmpresa());
        event.setLocal(eventDTO.getLocal());
        event.setResponsavel(eventDTO.getResponsavel());
        event.setData(eventDTO.getData());
        event.setCreatedAt(Instant.now());
        return event;
    }
}
