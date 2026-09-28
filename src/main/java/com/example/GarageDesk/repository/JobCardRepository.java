package com.example.GarageDesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GarageDesk.entity.JobCard;
import com.example.GarageDesk.entity.JobStatus;

public interface JobCardRepository extends JpaRepository<JobCard, Long> {

    List<JobCard> findByBayIdAndJobStatusIn(
            Long bayId,
            List<JobStatus> statuses
    );
}