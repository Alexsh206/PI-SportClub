package com.sportclub.service;

import com.sportclub.model.Coach;
import com.sportclub.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;

    public Coach create(Coach coach) {
        return coachRepository.save(coach);
    }

    public Coach getById(Long id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coach not found"));
    }

    public List<Coach> getAll() {
        return coachRepository.findAll();
    }

    public Coach update(Long id, Coach updated) {
        Coach coach = getById(id);

        coach.setFullName(updated.getFullName());
        coach.setEmail(updated.getEmail());
        coach.setPhone(updated.getPhone());
        coach.setTeam(updated.getTeam());

        return coachRepository.save(coach);
    }

    public void delete(Long id) {
        coachRepository.deleteById(id);
    }
}
