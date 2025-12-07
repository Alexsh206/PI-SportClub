package com.sportclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties({"ticket", "hall"})
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @JsonIgnoreProperties({"tickets", "participants", "results", "onlineAccess"})
    private Event event;

    @ManyToOne
    @JoinColumn(name = "spectator_id")
    @JsonIgnore
    private Spectator spectator;

    @Column(nullable = false)
    private String status;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;
}
