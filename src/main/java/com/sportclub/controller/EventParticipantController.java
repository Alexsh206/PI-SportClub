package com.sportclub.controller;

import com.sportclub.model.EventParticipant;
import com.sportclub.service.EventParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-participants")
@RequiredArgsConstructor
public class EventParticipantController {

    private final EventParticipantService participantService;

    @PostMapping("/create")
    public ResponseEntity<EventParticipant> create(@RequestBody EventParticipant participant) {
        return ResponseEntity.ok(participantService.create(participant));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventParticipant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventParticipant>> getAll() {
        return ResponseEntity.ok(participantService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventParticipant> update(
            @PathVariable Long id,
            @RequestBody EventParticipant updated) {
        return ResponseEntity.ok(participantService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
