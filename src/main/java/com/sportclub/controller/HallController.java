package com.sportclub.controller;

import com.sportclub.model.Event;
import com.sportclub.model.Hall;
import com.sportclub.service.HallService;
import com.sportclub.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;
    private final SeatService seatService;

    @PostMapping("/create")
    public ResponseEntity<Hall> create(@RequestBody Hall hall) {
        return ResponseEntity.ok(hallService.create(hall));
    }

    @PostMapping("/create-full")
    public ResponseEntity<Hall> createFullHall(
            @RequestParam Long eventId,
            @RequestParam int rows,
            @RequestParam int seatsInRow,

            @RequestParam int vipFrom,
            @RequestParam int vipTo,
            @RequestParam double vipPrice,

            @RequestParam int standardFrom,
            @RequestParam int standardTo,
            @RequestParam double standardPrice,

            @RequestParam int economyFrom,
            @RequestParam int economyTo,
            @RequestParam double economyPrice
    ) {
        // 1️⃣ Створюємо зал, прив'язаний до події
        Hall hall = new Hall();
        hall.setEvent(Event.builder().id(eventId).build());
        hall.setRowsCount(rows);
        hall.setSeatsInRow(seatsInRow);

        hall = hallService.create(hall);

        // 2️⃣ Генеруємо сидіння для цього залу
        seatService.generateSeatRanges(
                hall.getId(),
                rows,
                seatsInRow,
                vipFrom, vipTo,
                standardFrom, standardTo,
                economyFrom, economyTo,
                vipPrice,
                standardPrice,
                economyPrice
        );

        return ResponseEntity.ok(hall);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hallService.getById(id));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Hall> getHallByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(hallService.getHallByEvent(eventId));
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
