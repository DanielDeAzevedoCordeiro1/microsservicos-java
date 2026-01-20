package com.example.events_api.domain.enums;

public enum OutBoxStatus {
    
    PENDING("PENDING"),
    PROCESSED("PROCESSED"),
    FAILED("FAILED");

    private final String value;

    OutBoxStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}