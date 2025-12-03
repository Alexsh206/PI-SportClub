package com.sportclub.repository;

import com.sportclub.model.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpectatorRepository extends JpaRepository<Spectator, Long> {
    Optional<Spectator> findByEmailAndPassword(String email, String password);
}
