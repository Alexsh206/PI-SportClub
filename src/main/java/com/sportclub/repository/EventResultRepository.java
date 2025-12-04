package com.sportclub.repository;

import com.sportclub.model.EventResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventResultRepository extends JpaRepository<EventResult, Long> {
    List<EventResult> findByEvent_Id(Long eventId);
}
