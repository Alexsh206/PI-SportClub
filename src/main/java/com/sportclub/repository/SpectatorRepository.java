package com.sportclub.repository;

import com.sportclub.model.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpectatorRepository extends JpaRepository<Spectator, Long> {
}
