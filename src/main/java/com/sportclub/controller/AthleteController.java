package com.sportclub.controller;

import com.sportclub.model.Athlete;
import com.sportclub.service.AthleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
@RequiredArgsConstructor
public class AthleteController {

    private final AthleteService athleteService;

    @PostMapping("/create")
    public ResponseEntity<Athlete> create(@RequestBody Athlete athlete) {
        return ResponseEntity.ok(athleteService.create(athlete));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getById(@PathVariable Long id) {
        return ResponseEntity.ok(athleteService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Athlete>> getAll() {
        return ResponseEntity.ok(athleteService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Athlete> update(
            @PathVariable Long id,
            @RequestBody Athlete athlete) {
        return ResponseEntity.ok(athleteService.update(id, athlete));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        athleteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
