package com.sportclub.service;

import com.sportclub.model.Athlete;
import com.sportclub.repository.AthleteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository athleteRepository;

    public Athlete create(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    public Athlete getById(Long id) {
        return athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Athlete not found"));
    }

    public List<Athlete> getAll() {
        return athleteRepository.findAll();
    }

    public Athlete update(Long id, Athlete updated) {
        Athlete athlete = getById(id);

        athlete.setFullName(updated.getFullName());
        athlete.setEmail(updated.getEmail());
        athlete.setPhone(updated.getPhone());
        athlete.setBirthDate(updated.getBirthDate());

        return athleteRepository.save(athlete);
    }

    public void delete(Long id) {
        athleteRepository.deleteById(id);
    }

    public Optional<Athlete> login(String email, String password) {
        return athleteRepository.findByEmailAndPassword(email, password);
    }
}
