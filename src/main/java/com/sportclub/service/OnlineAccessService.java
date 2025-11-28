package com.sportclub.service;

import com.sportclub.model.OnlineAccess;
import com.sportclub.repository.OnlineAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OnlineAccessService {

    private final OnlineAccessRepository onlineAccessRepository;

    public OnlineAccess create(OnlineAccess access) {
        return onlineAccessRepository.save(access);
    }

    public OnlineAccess getById(Long id) {
        return onlineAccessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Online access not found"));
    }

    public List<OnlineAccess> getAll() {
        return onlineAccessRepository.findAll();
    }

    public OnlineAccess update(Long id, OnlineAccess updated) {
        OnlineAccess access = getById(id);

        access.setEvent(updated.getEvent());
        access.setSpectator(updated.getSpectator());
        access.setActivatedAt(updated.getActivatedAt());
        access.setIsActive(updated.getIsActive());

        return onlineAccessRepository.save(access);
    }

    public void delete(Long id) {
        onlineAccessRepository.deleteById(id);
    }
}
