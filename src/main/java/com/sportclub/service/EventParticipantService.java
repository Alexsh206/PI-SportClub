package com.sportclub.service;

import com.sportclub.model.EventParticipant;
import com.sportclub.repository.EventParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventParticipantService {

    private final EventParticipantRepository eventParticipantRepository;

    public EventParticipant create(EventParticipant participant) {
        return eventParticipantRepository.save(participant);
    }

    public EventParticipant getById(Long id) {
        return eventParticipantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event participant not found"));
    }

    public List<EventParticipant> getAll() {
        return eventParticipantRepository.findAll();
    }

    public EventParticipant update(Long id, EventParticipant updated) {
        EventParticipant participant = getById(id);

        participant.setEvent(updated.getEvent());
        participant.setParticipantType(updated.getParticipantType());
        participant.setTeam(updated.getTeam());
        participant.setAthlete(updated.getAthlete());

        return eventParticipantRepository.save(participant);
    }

    public void delete(Long id) {
        eventParticipantRepository.deleteById(id);
    }
}
