package com.sportclub.service;

import com.sportclub.model.Seat;
import com.sportclub.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    public Seat create(Seat seat) {
        return seatRepository.save(seat);
    }

    public Seat getById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
    }

    public List<Seat> getAll() {
        return seatRepository.findAll();
    }

    public Seat update(Long id, Seat updated) {
        Seat seat = getById(id);

        seat.setHall(updated.getHall());
        seat.setRowNumber(updated.getRowNumber());
        seat.setSeatNumber(updated.getSeatNumber());
        seat.setSeatType(updated.getSeatType());
        seat.setPrice(updated.getPrice());

        return seatRepository.save(seat);
    }

    public void delete(Long id) {
        seatRepository.deleteById(id);
    }
}
