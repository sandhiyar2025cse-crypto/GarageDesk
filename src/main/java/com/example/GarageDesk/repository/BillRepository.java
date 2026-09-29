package com.example.GarageDesk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GarageDesk.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByJobCardId(Long jobCardId);
}