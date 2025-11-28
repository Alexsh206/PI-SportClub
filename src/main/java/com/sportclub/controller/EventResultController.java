package com.sportclub.controller;

import com.sportclub.model.EventResult;
import com.sportclub.service.EventResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-results")
@RequiredArgsConstructor
public class EventResultController {

    private final EventResultService eventResultService;

    @PostMapping("/create")
    public ResponseEntity<EventResult> create(@RequestBody EventResult result) {
        return ResponseEntity.ok(eventResultService.create(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResult> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventResultService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResult>> getAll() {
        return ResponseEntity.ok(eventResultService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventResult> update(
            @PathVariable Long id,
            @RequestBody EventResult updated) {
        return ResponseEntity.ok(eventResultService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventResultService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
