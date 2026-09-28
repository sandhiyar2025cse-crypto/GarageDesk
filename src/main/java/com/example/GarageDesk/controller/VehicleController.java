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

import com.example.GarageDesk.entity.Vehicle;
import com.example.GarageDesk.service.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(
            @Valid @RequestBody Vehicle vehicle) {

        Vehicle savedVehicle = vehicleService.createVehicle(vehicle);

        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    // READ - all vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        return ResponseEntity.ok(
                vehicleService.getAllVehicles()
        );
    }

    // READ - vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                vehicleService.getVehicleById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody Vehicle vehicle) {

        return ResponseEntity.ok(
                vehicleService.updateVehicle(id, vehicle)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(
            @PathVariable Long id) {

        vehicleService.deleteVehicle(id);

        return ResponseEntity.ok(
                "Vehicle deleted successfully"
        );
    }
}