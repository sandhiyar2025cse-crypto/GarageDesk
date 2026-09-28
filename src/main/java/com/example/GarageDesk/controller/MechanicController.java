package com.example.GarageDesk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GarageDesk.entity.Mechanic;
import com.example.GarageDesk.service.MechanicService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {

    private final MechanicService service;

    public MechanicController(MechanicService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mechanic> create(@Valid @RequestBody Mechanic mechanic) {
        return new ResponseEntity<>(service.create(mechanic), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Mechanic> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mechanic getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Mechanic update(@PathVariable Long id,
                           @Valid @RequestBody Mechanic mechanic) {
        return service.update(id, mechanic);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Mechanic deleted successfully";
    }
}