package com.sportclub.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamMemberId implements Serializable {

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "athlete_id")
    private Long athleteId;
}
