package com.example.events_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.events_api.domain.OutBox;

public interface OutBoxRepository extends JpaRepository<OutBox, Long> {
    
    List<OutBox> findAll();
}