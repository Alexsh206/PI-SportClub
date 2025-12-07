package com.sportclub.repository;

import com.sportclub.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllBySpectatorId(Long spectatorId);
}
