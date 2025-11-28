package com.sportclub.controller;

import com.sportclub.model.Spectator;
import com.sportclub.service.SpectatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spectators")
@RequiredArgsConstructor
public class SpectatorController {

    private final SpectatorService spectatorService;

    @PostMapping("/create")
    public ResponseEntity<Spectator> create(@RequestBody Spectator spectator) {
        return ResponseEntity.ok(spectatorService.create(spectator));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spectator> getById(@PathVariable Long id) {
        return ResponseEntity.ok(spectatorService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Spectator>> getAll() {
        return ResponseEntity.ok(spectatorService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Spectator> update(
            @PathVariable Long id,
            @RequestBody Spectator spectator) {
        return ResponseEntity.ok(spectatorService.update(id, spectator));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        spectatorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
