package com.sportclub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seats")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column(name = "row_number", nullable = false)
    private Integer rowNumber;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "seat_type", nullable = false)
    private String seatType; // front / middle / back

    @Column(nullable = false)
    private Double price;

    @OneToOne(mappedBy = "seat", cascade = CascadeType.ALL)
    private Ticket ticket;
}
