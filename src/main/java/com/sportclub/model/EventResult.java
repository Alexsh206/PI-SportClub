package com.sportclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_results")
@Data
@Getter
@Setter
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
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    @JsonIgnore
    private EventParticipant participant;

    @Column(nullable = false)
    private Integer score;

    @Column
    private Integer place;
}
