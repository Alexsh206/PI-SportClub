package com.sportclub.controller;

import com.sportclub.model.Ticket;
import com.sportclub.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.create(ticket));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAll() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ticket> update(
            @PathVariable Long id,
            @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.update(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- PURCHASE ---
    @PostMapping("/buy/{ticketId}/spectator/{spectatorId}")
    public ResponseEntity<Ticket> buyTicket(
            @PathVariable Long ticketId,
            @PathVariable Long spectatorId) {
        return ResponseEntity.ok(ticketService.buyTicket(ticketId, spectatorId));
    }

    // --- RETURN ---
    @PostMapping("/return/{ticketId}")
    public ResponseEntity<Ticket> returnTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.returnTicket(ticketId));
    }
}
