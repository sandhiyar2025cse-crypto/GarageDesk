package com.example.GarageDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GarageDesk.entity.Bay;

public interface BayRepository extends JpaRepository<Bay, Long> {
}