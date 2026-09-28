package com.example.GarageDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GarageDesk.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}