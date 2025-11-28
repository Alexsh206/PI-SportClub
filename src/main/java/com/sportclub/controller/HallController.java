package com.sportclub.controller;

import com.sportclub.model.Hall;
import com.sportclub.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @PostMapping("/create")
    public ResponseEntity<Hall> create(@RequestBody Hall hall) {
        return ResponseEntity.ok(hallService.create(hall));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hallService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hall>> getAll() {
        return ResponseEntity.ok(hallService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hall> update(
            @PathVariable Long id,
            @RequestBody Hall updated) {
        return ResponseEntity.ok(hallService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hallService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
