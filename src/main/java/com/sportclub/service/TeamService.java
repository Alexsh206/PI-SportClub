package com.sportclub.service;

import com.sportclub.model.Team;
import com.sportclub.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Team create(Team team) {
        return teamRepository.save(team);
    }

    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public Team update(Long id, Team updated) {
        Team team = getById(id);

        team.setTeamName(updated.getTeamName());
        team.setCoach(updated.getCoach());

        return teamRepository.save(team);
    }

    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
