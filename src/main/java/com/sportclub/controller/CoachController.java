package com.sportclub.controller;

import com.sportclub.model.Coach;
import com.sportclub.service.CoachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @PostMapping("/create")
    public ResponseEntity<Coach> create(@RequestBody Coach coach) {
        return ResponseEntity.ok(coachService.create(coach));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> getById(@PathVariable Long id) {
        return ResponseEntity.ok(coachService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Coach>> getAll() {
        return ResponseEntity.ok(coachService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Coach> update(
            @PathVariable Long id,
            @RequestBody Coach coach) {
        return ResponseEntity.ok(coachService.update(id, coach));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coachService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
