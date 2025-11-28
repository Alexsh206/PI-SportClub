package com.sportclub.service;

import com.sportclub.model.Spectator;
import com.sportclub.repository.SpectatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpectatorService {

    private final SpectatorRepository spectatorRepository;

    public Spectator create(Spectator spectator) {
        return spectatorRepository.save(spectator);
    }

    public Spectator getById(Long id) {
        return spectatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Spectator not found"));
    }

    public List<Spectator> getAll() {
        return spectatorRepository.findAll();
    }

    public Spectator update(Long id, Spectator updated) {
        Spectator spectator = getById(id);

        spectator.setFullName(updated.getFullName());
        spectator.setEmail(updated.getEmail());
        spectator.setPhone(updated.getPhone());
        spectator.setStatus(updated.getStatus());

        return spectatorRepository.save(spectator);
    }

    public void delete(Long id) {
        spectatorRepository.deleteById(id);
    }
}
