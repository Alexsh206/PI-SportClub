package com.sportclub.controller;

import com.sportclub.model.TeamMember;
import com.sportclub.model.TeamMemberId;
import com.sportclub.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-members")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @PostMapping("/add")
    public ResponseEntity<TeamMember> add(@RequestBody TeamMember teamMember) {
        return ResponseEntity.ok(teamMemberService.addMember(teamMember));
    }

    @GetMapping("/{teamId}/{athleteId}")
    public ResponseEntity<TeamMember> getById(
            @PathVariable Long teamId,
            @PathVariable Long athleteId
    ) {
        TeamMemberId id = new TeamMemberId(teamId, athleteId);
        return ResponseEntity.ok(teamMemberService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeamMember>> getAll() {
        return ResponseEntity.ok(teamMemberService.getAll());
    }

    @DeleteMapping("/{teamId}/{athleteId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long teamId,
            @PathVariable Long athleteId
    ) {
        teamMemberService.remove(new TeamMemberId(teamId, athleteId));
        return ResponseEntity.noContent().build();
    }
}
