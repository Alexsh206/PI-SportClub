package com.sportclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "spectator_id")
    private Spectator spectator;

    @Column(nullable = false)
    private String status;
    // available / reserved / sold / returned

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;
}
