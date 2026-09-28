package com.example.GarageDesk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.GarageDesk.entity.Bay;
import com.example.GarageDesk.repository.BayRepository;

@Service
public class BayService {

    private final BayRepository repository;

    public BayService(BayRepository repository) {
        this.repository = repository;
    }

    public Bay create(Bay bay) {
        return repository.save(bay);
    }

    public List<Bay> getAll() {
        return repository.findAll();
    }

    public Bay getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bay not found with id: " + id));
    }

    public Bay update(Long id, Bay updated) {
        Bay existing = getById(id);

        existing.setBayNumber(updated.getBayNumber());
        existing.setBayType(updated.getBayType());
        existing.setOccupied(updated.isOccupied());

        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Bay not found with id: " + id);
        }
        repository.deleteById(id);
    }
}