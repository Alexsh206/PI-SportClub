package com.sportclub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "spectators")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spectator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spectator_id")
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // або passwordHash, як захочеш

    @Column
    private String phone;

    @Column(nullable = false)
    private String status; // active / blocked – можна потім винести в enum

    // --- Зв’язки ---

    @OneToMany(mappedBy = "spectator", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "spectator", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<OnlineAccess> onlineAccesses;
}