package com.example.producer.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "outbox")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OutBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID eventId;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private String eventType;

    private String status;

    public void markAsSent() {
        this.status = "SENT";
    }

    public void markAsError() {
        this.status = "ERROR";
    }
}

