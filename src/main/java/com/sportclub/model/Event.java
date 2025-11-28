package com.sportclub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "sport_type", nullable = false)
    private String sportType;

    @Column(name = "tournament_name")
    private String tournamentName;

    @Column(name = "event_date", nullable = false)
    private LocalDate date;

    @Column(name = "event_time", nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String status; // scheduled / finished / canceled

    // --- Relationships ---

    @OneToMany(mappedBy = "event")
    private List<EventParticipant> participants;

    @OneToMany(mappedBy = "event")
    private List<EventResult> results;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Hall hall;

    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "event")
    private List<OnlineAccess> onlineAccess;
}
