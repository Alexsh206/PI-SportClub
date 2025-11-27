package com.sportclub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private EventParticipant participant;

    @Column(nullable = false)
    private Integer score;

    @Column
    private Integer place;
}
