package com.sportclub.service;

import com.sportclub.model.*;
import com.sportclub.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;
    private final SpectatorRepository spectatorRepository;

    public Payment processPayment(Long spectatorId, Long eventId, Long seatId, Double amount) {

        log.info("Starting payment: spectator={}, event={}, seat={}", spectatorId, eventId, seatId);

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if ("SOLD".equals(seat.getStatus())) {
            throw new RuntimeException("Seat already sold");
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Spectator spectator = spectatorRepository.findById(spectatorId)
                .orElseThrow(() -> new RuntimeException("Spectator not found"));

        Payment payment = Payment.builder()
                .spectatorId(spectatorId)
                .eventId(eventId)
                .seatId(seatId)
                .amount(amount)
                .paidAt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);
        log.info("Payment saved: {}", payment.getId());

        Ticket ticket = Ticket.builder()
                .seat(seat)
                .event(event)
                .spectator(spectator)
                .status("ACTIVE")
                .purchaseDate(LocalDateTime.now())
                .build();

        Ticket saved = ticketRepository.save(ticket);
        log.info("Ticket created: {}", saved.getId());

        seat.setStatus("SOLD");
        seat.setTicket(saved);
        seatRepository.save(seat);

        log.info("Seat {} marked SOLD", seatId);

        return payment;
    }
}
