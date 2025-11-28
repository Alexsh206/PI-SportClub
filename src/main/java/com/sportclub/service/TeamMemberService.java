package com.sportclub.service;

import com.sportclub.model.TeamMember;
import com.sportclub.model.TeamMemberId;
import com.sportclub.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMember addMember(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    public TeamMember getById(TeamMemberId id) {
        return teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found"));
    }

    public List<TeamMember> getAll() {
        return teamMemberRepository.findAll();
    }

    public void remove(TeamMemberId id) {
        teamMemberRepository.deleteById(id);
    }
}
