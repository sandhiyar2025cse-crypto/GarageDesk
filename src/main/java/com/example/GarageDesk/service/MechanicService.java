package com.example.GarageDesk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.GarageDesk.entity.Mechanic;
import com.example.GarageDesk.repository.MechanicRepository;

@Service
public class MechanicService {

    private final MechanicRepository repository;

    public MechanicService(MechanicRepository repository) {
        this.repository = repository;
    }

    public Mechanic create(Mechanic mechanic) {
        return repository.save(mechanic);
    }

    public List<Mechanic> getAll() {
        return repository.findAll();
    }

    public Mechanic getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mechanic not found with id: " + id));
    }

    public Mechanic update(Long id, Mechanic updated) {
        Mechanic existing = getById(id);

        existing.setName(updated.getName());
        existing.setPhoneNumber(updated.getPhoneNumber());
        existing.setSpecialization(updated.getSpecialization());
        existing.setAvailable(updated.isAvailable());

        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Mechanic not found with id: " + id);
        }
        repository.deleteById(id);
    }
}