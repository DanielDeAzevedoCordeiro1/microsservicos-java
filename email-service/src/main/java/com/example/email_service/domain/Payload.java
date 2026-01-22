package com.example.email_service.domain;

import java.io.Serializable;

public record Payload(
    String message
) implements Serializable {
}
