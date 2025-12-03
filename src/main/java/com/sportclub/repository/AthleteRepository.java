package com.sportclub.repository;

import com.sportclub.model.Admin;
import com.sportclub.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Optional<Athlete> findByEmailAndPassword(String email, String password);
}
