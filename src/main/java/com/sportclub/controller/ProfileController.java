package com.sportclub.controller;

import com.sportclub.config.JwtConfig;
import com.sportclub.model.Spectator;
import com.sportclub.model.Athlete;
import com.sportclub.model.Admin;
import com.sportclub.service.SpectatorService;
import com.sportclub.service.AthleteService;
import com.sportclub.service.AdminService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class ProfileController {

    private final SpectatorService spectatorService;
    private final AthleteService athleteService;
    private final AdminService adminService;
    private final JwtConfig jwtConfig;

    public ProfileController(SpectatorService spectatorService,
                             AthleteService athleteService,
                             AdminService adminService,
                             JwtConfig jwtConfig) {
        this.spectatorService = spectatorService;
        this.athleteService   = athleteService;
        this.adminService     = adminService;
        this.jwtConfig        = jwtConfig;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Missing token"));
        }

        String token = authHeader.substring(7);

        try {
            Jws<Claims> jwt = jwtConfig.parseToken(token);
            Claims body = jwt.getBody();

            String role = body.get("role", String.class);
            long id = Long.parseLong(body.getSubject());

            /* ====================================================
                           SPECTATOR PROFILE
            ==================================================== */
            if ("spectator".equals(role)) {
                Spectator s = spectatorService.getById(id);

                return ResponseEntity.ok(Map.of(
                        "id", s.getId(),
                        "fullName", s.getFullName(),
                        "email", s.getEmail(),
                        "phone", s.getPhone(),
                        "role", "spectator"
                ));
            }

            /* ====================================================
                           SPORTSMAN PROFILE (athlete)
            ==================================================== */
            if ("sportsman".equals(role)) {
                Athlete a = athleteService.getById(id);

                return ResponseEntity.ok(Map.of(
                        "id", a.getId(),
                        "fullName", a.getFullName(),
                        "team", a.getTeam(),
                        "email", a.getEmail(),
                        "role", "sportsman"
                ));
            }

            /* ====================================================
                           ADMIN PROFILE
            ==================================================== */
            if ("admin".equals(role)) {
                Admin a = adminService.getById(id);

                return ResponseEntity.ok(Map.of(
                        "id", a.getAdminId(),
                        "email", a.getEmail(),
                        "fullName", a.getFullName(),
                        "role", "admin"
                ));
            }

            return ResponseEntity.status(401).body(Map.of("error", "Unknown role"));

        } catch (JwtException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid token"));
        }
    }
}
