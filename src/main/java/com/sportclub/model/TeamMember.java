package com.sportclub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "team_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMember {

    @EmbeddedId
    private TeamMemberId id;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @MapsId("athleteId")
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    @Column(name = "joined_at")
    private String LocalDate; // можна замінити на LocalDate
}
