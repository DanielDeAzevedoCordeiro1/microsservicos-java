package com.example.producer.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.producer.domain.OutBox;
import jakarta.persistence.LockModeType;

@Repository
public interface OutBoxRepository extends JpaRepository<OutBox, Long> {
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM OutBox o WHERE o.status = 'PENDING' order by o.id")
    List<OutBox> findPendingEvents();

    Optional<OutBox> findById(Long id);
    
}
