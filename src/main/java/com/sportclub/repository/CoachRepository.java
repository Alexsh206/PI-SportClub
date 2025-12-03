package com.sportclub.repository;

import com.sportclub.model.Athlete;
import com.sportclub.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByEmailAndPassword(String email, String password);
}
