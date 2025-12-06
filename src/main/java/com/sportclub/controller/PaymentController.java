package com.sportclub.controller;

import com.sportclub.model.Payment;
import com.sportclub.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Payment> pay(
            @RequestParam Long spectatorId,
            @RequestParam Long eventId,
            @RequestParam Long seatId
    ) {
        return ResponseEntity.ok(
                paymentService.processPayment(spectatorId, eventId, seatId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Payment>> getByEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(paymentService.getByEvent(eventId));
    }

    @GetMapping("/user/{spectatorId}")
    public ResponseEntity<List<Payment>> getByUser(@PathVariable Long spectatorId) {
        return ResponseEntity.ok(paymentService.getByUser(spectatorId));
    }
}
