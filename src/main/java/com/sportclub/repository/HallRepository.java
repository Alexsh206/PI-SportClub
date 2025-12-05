package com.sportclub.repository;

import com.sportclub.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findByEventId(Long eventId);
}
