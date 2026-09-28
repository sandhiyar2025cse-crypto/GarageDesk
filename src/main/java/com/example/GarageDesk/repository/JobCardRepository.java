package com.example.GarageDesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GarageDesk.entity.JobCard;

public interface JobCardRepository extends JpaRepository<JobCard, Long> {
}