package com.sportclub.service;

import com.sportclub.model.EventResult;
import com.sportclub.repository.EventResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventResultService {

    private final EventResultRepository eventResultRepository;

    public EventResult create(EventResult result) {
        return eventResultRepository.save(result);
    }

    public EventResult getById(Long id) {
        return eventResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event result not found"));
    }

    public List<EventResult> getAll() {
        return eventResultRepository.findAll();
    }

    public EventResult update(Long id, EventResult updated) {
        EventResult result = getById(id);

        result.setEvent(updated.getEvent());
        result.setParticipant(updated.getParticipant());
        result.setScore(updated.getScore());
        result.setPlace(updated.getPlace());

        return eventResultRepository.save(result);
    }

    public void delete(Long id) {
        eventResultRepository.deleteById(id);
    }
}
