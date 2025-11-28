package com.sportclub.service;

import com.sportclub.model.Hall;
import com.sportclub.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService {

    private final HallRepository hallRepository;

    public Hall create(Hall hall) {
        return hallRepository.save(hall);
    }

    public Hall getById(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hall not found"));
    }

    public List<Hall> getAll() {
        return hallRepository.findAll();
    }

    public Hall update(Long id, Hall updated) {
        Hall hall = getById(id);

        hall.setEvent(updated.getEvent());
        hall.setRowsCount(updated.getRowsCount());
        hall.setSeatsInRow(updated.getSeatsInRow());

        return hallRepository.save(hall);
    }

    public void delete(Long id) {
        hallRepository.deleteById(id);
    }
}
