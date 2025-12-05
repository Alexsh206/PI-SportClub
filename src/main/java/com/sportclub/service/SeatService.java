package com.sportclub.service;

import com.sportclub.model.Hall;
import com.sportclub.model.Seat;
import com.sportclub.repository.HallRepository;
import com.sportclub.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final HallRepository hallRepository;

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

    // --------------------------------------------------------
    //  Генерація сидінь за діапазонами (VIP / Standard / Economy)
    // --------------------------------------------------------
    public void generateSeatRanges(
            Long hallId,
            int rows,
            int seatsInRow,
            int vipFrom, int vipTo,
            int standardFrom, int standardTo,
            int economyFrom, int economyTo,
            double vipPrice,
            double standardPrice,
            double economyPrice
    ) {
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new RuntimeException("Hall not found"));

        for (int r = 1; r <= rows; r++) {
            for (int s = 1; s <= seatsInRow; s++) {

                Seat seat = new Seat();
                seat.setHall(hall);
                seat.setRowNumber(r);
                seat.setSeatNumber(s);

                if (r >= vipFrom && r <= vipTo) {
                    seat.setSeatType("VIP");
                    seat.setPrice(vipPrice);
                } else if (r >= standardFrom && r <= standardTo) {
                    seat.setSeatType("Standard");
                    seat.setPrice(standardPrice);
                } else if (r >= economyFrom && r <= economyTo) {
                    seat.setSeatType("Economy");
                    seat.setPrice(economyPrice);
                } else {
                    seat.setSeatType("Unknown");
                    seat.setPrice(0.0);
                }

                seatRepository.save(seat);
            }
        }
    }
}
