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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GarageDesk.entity.JobCard;
import com.example.GarageDesk.entity.JobStatus;
import com.example.GarageDesk.service.JobCardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobcards")
public class JobCardController {

    private final JobCardService jobCardService;

    public JobCardController(JobCardService jobCardService) {
        this.jobCardService = jobCardService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<JobCard> createJobCard(
            @Valid @RequestBody JobCard jobCard) {

        JobCard savedJobCard = jobCardService.create(jobCard);

        return new ResponseEntity<>(
                savedJobCard,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<JobCard>> getAllJobCards() {

        return ResponseEntity.ok(
                jobCardService.getAll()
        );
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<JobCard> getJobCardById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                jobCardService.getById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<JobCard> updateJobCard(
            @PathVariable Long id,
            @Valid @RequestBody JobCard jobCard) {

        return ResponseEntity.ok(
                jobCardService.update(id, jobCard)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobCard(
            @PathVariable Long id) {

        jobCardService.delete(id);

        return ResponseEntity.ok(
                "Job card deleted successfully"
        );
    }

    // ASSIGN BAY + MECHANIC
    @PostMapping("/{id}/assign")
    public ResponseEntity<JobCard> assignBayAndMechanic(
            @PathVariable Long id,
            @RequestParam Long bayId,
            @RequestParam Long mechanicId) {

        return ResponseEntity.ok(
                jobCardService.assign(
                        id,
                        bayId,
                        mechanicId
                )
        );
    }

    // UPDATE JOB STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<JobCard> updateStatus(
            @PathVariable Long id,
            @RequestParam JobStatus status) {

        return ResponseEntity.ok(
                jobCardService.updateStatus(id, status)
        );
    }

    // QUALITY CHECK
    @PutMapping("/{id}/quality-check")
    public ResponseEntity<JobCard> performQualityCheck(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                jobCardService.performQualityCheck(id)
        );
    }
}