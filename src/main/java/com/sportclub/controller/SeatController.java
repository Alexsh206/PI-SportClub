package com.sportclub.controller;

import com.sportclub.model.Seat;
import com.sportclub.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/create")
    public ResponseEntity<Seat> create(@RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.create(seat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getById(@PathVariable Long id) {
        return ResponseEntity.ok(seatService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Seat>> getAll() {
        return ResponseEntity.ok(seatService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Seat> update(
            @PathVariable Long id,
            @RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.update(id, seat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seatService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Якщо хочеш залишити окремий генератор
    @PostMapping("/generate-range")
    public ResponseEntity<String> generateSeatRanges(
            @RequestParam Long hallId,
            @RequestParam int rows,
            @RequestParam int seatsInRow,
            @RequestParam int vipFrom,
            @RequestParam int vipTo,
            @RequestParam int standardFrom,
            @RequestParam int standardTo,
            @RequestParam int economyFrom,
            @RequestParam int economyTo,
            @RequestParam double vipPrice,
            @RequestParam double standardPrice,
            @RequestParam double economyPrice
    ) {
        seatService.generateSeatRanges(
                hallId, rows, seatsInRow,
                vipFrom, vipTo,
                standardFrom, standardTo,
                economyFrom, economyTo,
                vipPrice, standardPrice, economyPrice
        );

        return ResponseEntity.ok("Seat ranges generated successfully");
    }
}
