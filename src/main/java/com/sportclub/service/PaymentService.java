package com.sportclub.service;

import com.sportclub.model.Payment;
import com.sportclub.model.Seat;
import com.sportclub.repository.PaymentRepository;
import com.sportclub.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SeatRepository seatRepository;

    public Payment processPayment(Long spectatorId, Long eventId, Long seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus().equals("SOLD")) {
            throw new RuntimeException("Seat is already sold");
        }

        seat.setStatus("SOLD");
        seatRepository.save(seat);

        Payment payment = Payment.builder()
                .spectatorId(spectatorId)
                .eventId(eventId)
                .seatId(seatId)
                .amount(seat.getPrice())
                .paidAt(LocalDateTime.now())
                .build();

        return paymentRepository.save(payment);
    }

    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public List<Payment> getByEvent(Long eventId) {
        return paymentRepository.findByEventId(eventId);
    }

    public List<Payment> getByUser(Long spectatorId) {
        return paymentRepository.findBySpectatorId(spectatorId);
    }
}
