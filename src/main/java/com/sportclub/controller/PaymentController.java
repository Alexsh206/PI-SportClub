package com.sportclub.controller;

import com.sportclub.model.Payment;
import com.sportclub.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Payment> pay(
            @RequestParam Long spectatorId,
            @RequestParam Long eventId,
            @RequestParam Long seatId,
            @RequestParam Double amount
    ) {
        return ResponseEntity.ok(
                paymentService.processPayment(spectatorId, eventId, seatId, amount)
        );
    }
}
