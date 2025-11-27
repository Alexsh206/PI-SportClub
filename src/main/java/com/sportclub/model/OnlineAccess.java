package com.sportclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "online_access")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnlineAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spectator_id", nullable = false)
    private Spectator spectator;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "activated_at", nullable = false)
    private LocalDateTime activatedAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
