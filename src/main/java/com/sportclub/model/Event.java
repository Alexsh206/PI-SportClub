package com.sportclub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"participants", "results", "tickets", "onlineAccess", "hall"})
@EqualsAndHashCode(exclude = {"participants", "results", "tickets", "onlineAccess", "hall"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private String title;

    @Column(name = "sport_type")
    private String sportType;

    private String tournamentName;

    @Column(name = "event_date")
    private LocalDate date;

    @Column(name = "event_time")
    private LocalTime time;

    private String location;

    private String status;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<EventParticipant> participants;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<EventResult> results;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private Hall hall;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<OnlineAccess> onlineAccess;
}