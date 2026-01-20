package com.example.events_api.domain.enums;

public enum EventType {

    EVENT_CREATED("EVENT_CREATED"),
    EVENT_UPDATED("EVENT_UPDATED"),
    EVENT_DELETED("EVENT_DELETED");

    private String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}