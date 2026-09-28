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

import com.example.GarageDesk.entity.Bay;
import com.example.GarageDesk.service.BayService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bays")
public class BayController {

    private final BayService bayService;

    public BayController(BayService bayService) {
        this.bayService = bayService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Bay> createBay(
            @Valid @RequestBody Bay bay) {

        Bay savedBay = bayService.create(bay);

        return new ResponseEntity<>(savedBay, HttpStatus.CREATED);
    }

    // READ - all bays
    @GetMapping
    public ResponseEntity<List<Bay>> getAllBays() {

        return ResponseEntity.ok(
                bayService.getAll()
        );
    }

    // READ - bay by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bay> getBayById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bayService.getById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Bay> updateBay(
            @PathVariable Long id,
            @Valid @RequestBody Bay bay) {

        return ResponseEntity.ok(
                bayService.update(id, bay)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBay(
            @PathVariable Long id) {

        bayService.delete(id);

        return ResponseEntity.ok(
                "Bay deleted successfully"
        );
    }
}