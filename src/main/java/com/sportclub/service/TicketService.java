package com.sportclub.service;

import com.sportclub.model.Ticket;
import com.sportclub.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Ticket update(Long id, Ticket updated) {
        Ticket ticket = getById(id);

        ticket.setSeat(updated.getSeat());
        ticket.setEvent(updated.getEvent());
        ticket.setSpectator(updated.getSpectator());
        ticket.setStatus(updated.getStatus());
        ticket.setPurchaseDate(updated.getPurchaseDate());
        ticket.setReturnDate(updated.getReturnDate());

        return ticketRepository.save(ticket);
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket buyTicket(Long id, Long spectatorId) {
        Ticket ticket = getById(id);

        if (!ticket.getStatus().equals("available")) {
            throw new RuntimeException("Ticket not available");
        }

        ticket.setStatus("sold");
        ticket.setPurchaseDate(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public Ticket returnTicket(Long id) {
        Ticket ticket = getById(id);

        if (!ticket.getStatus().equals("sold")) {
            throw new RuntimeException("Ticket cannot be returned");
        }

        ticket.setStatus("returned");
        ticket.setReturnDate(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }
}
