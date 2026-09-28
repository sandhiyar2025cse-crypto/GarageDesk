package com.example.GarageDesk.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GarageDesk.entity.Vehicle;
import com.example.GarageDesk.repository.VehicleRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // CREATE
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // READ - all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // READ - by ID
    public Vehicle getVehicleById(Long id) {

        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vehicle not found with id: " + id
                ));
    }

    // UPDATE
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {

        Vehicle existingVehicle = getVehicleById(id);

        existingVehicle.setRegistrationNumber(
                updatedVehicle.getRegistrationNumber()
        );

        existingVehicle.setOwnerName(
                updatedVehicle.getOwnerName()
        );

        existingVehicle.setPhoneNumber(
                updatedVehicle.getPhoneNumber()
        );

        existingVehicle.setModel(
                updatedVehicle.getModel()
        );

        existingVehicle.setVehicleType(
                updatedVehicle.getVehicleType()
        );

        return vehicleRepository.save(existingVehicle);
    }

    // DELETE
    public void deleteVehicle(Long id) {

        if (!vehicleRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Vehicle not found with id: " + id
            );
        }

        vehicleRepository.deleteById(id);
    }
}