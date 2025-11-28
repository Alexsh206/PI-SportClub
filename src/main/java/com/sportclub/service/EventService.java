package com.sportclub.service;

import com.sportclub.model.Event;
import com.sportclub.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Event update(Long id, Event updated) {
        Event event = getById(id);

        event.setTitle(updated.getTitle());
        event.setSportType(updated.getSportType());
        event.setTournamentName(updated.getTournamentName());
        event.setDate(updated.getDate());
        event.setTime(updated.getTime());
        event.setLocation(updated.getLocation());
        event.setStatus(updated.getStatus());

        return eventRepository.save(event);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
