package com.example.GarageDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GarageDesk.entity.Mechanic;

public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
}