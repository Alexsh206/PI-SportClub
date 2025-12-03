package com.sportclub.controller;

import com.sportclub.config.JwtConfig;
import com.sportclub.service.SpectatorService;
import com.sportclub.service.AthleteService;
import com.sportclub.service.AdminService;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    private final SpectatorService spectatorService;
    private final AthleteService athleteService;
    private final AdminService adminService;
    private final JwtConfig jwtConfig;

    public AuthController(SpectatorService spectatorService,
                          AthleteService athleteService,
                          AdminService adminService,
                          JwtConfig jwtConfig) {
        this.spectatorService = spectatorService;
        this.athleteService = athleteService;
        this.adminService = adminService;
        this.jwtConfig = jwtConfig;
    }

    /* ======================================================
                     LOGIN (API)
    ======================================================= */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest creds) {
        return doLogin(creds);
    }

    /* ======================================================
                    CORE LOGIN LOGIC
    ======================================================= */

    private ResponseEntity<?> doLogin(LoginRequest creds) {

        // 1) LOGIN AS SPECTATOR
        return spectatorService.login(creds.getEmail(), creds.getPassword())
                .map(s -> buildResponse(
                        s.getId(),
                        "spectator",
                        Map.of(
                                "fullName", s.getFullName(),
                                "email", s.getEmail()
                        )))
                // 2) LOGIN AS ATHLETE
                .or(() -> athleteService.login(creds.getEmail(), creds.getPassword())
                        .map(a -> buildResponse(
                                a.getId(),
                                "sportsman",
                                Map.of(
                                        "fullName", a.getFullName(),
                                        "team", a.getTeam()
                                ))))
                // 3) LOGIN AS ADMIN
                .or(() -> adminService.login(creds.getEmail(), creds.getPassword())
                        .map(ad -> buildResponse(
                                ad.getAdminId(),
                                "admin",
                                Map.of("email", ad.getEmail())
                        )))
                .orElseGet(() ->
                        ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"))
                );
    }

    /* ======================================================
                    RESPONSE BUILDER
    ======================================================= */

    private ResponseEntity<Map<String, Object>> buildResponse(
            Long id,
            String role,
            Map<String, Object> extras
    ) {
        String token = jwtConfig.createToken(Math.toIntExact(id), role);

        Map<String, Object> response = new HashMap<>(extras);
        response.put("role", role);
        response.put("token", token);
        response.put("id", id);

        return ResponseEntity.ok(response);
    }

    /* ======================================================
                    LOGIN REQUEST MODEL
    ======================================================= */

    @Setter
    @Getter
    public static class LoginRequest {
        private String email;
        private String password;
    }
}
