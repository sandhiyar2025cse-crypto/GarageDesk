package com.example.GarageDesk.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.GarageDesk.entity.Bay;
import com.example.GarageDesk.entity.JobCard;
import com.example.GarageDesk.entity.JobStatus;
import com.example.GarageDesk.entity.Mechanic;
import com.example.GarageDesk.entity.Vehicle;
import com.example.GarageDesk.repository.BayRepository;
import com.example.GarageDesk.repository.JobCardRepository;
import com.example.GarageDesk.repository.MechanicRepository;
import com.example.GarageDesk.repository.VehicleRepository;

@Service
public class JobCardService {

    private final JobCardRepository jobCardRepository;
    private final VehicleRepository vehicleRepository;
    private final BayRepository bayRepository;
    private final MechanicRepository mechanicRepository;

    public JobCardService(
            JobCardRepository jobCardRepository,
            VehicleRepository vehicleRepository,
            BayRepository bayRepository,
            MechanicRepository mechanicRepository) {

        this.jobCardRepository = jobCardRepository;
        this.vehicleRepository = vehicleRepository;
        this.bayRepository = bayRepository;
        this.mechanicRepository = mechanicRepository;
    }

    // CREATE
    public JobCard create(JobCard jobCard) {

        Long vehicleId = jobCard.getVehicle().getId();

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vehicle not found"));

        jobCard.setVehicle(vehicle);
        jobCard.setJobStatus(JobStatus.CREATED);

        return jobCardRepository.save(jobCard);
    }

    // READ ALL
    public List<JobCard> getAll() {
        return jobCardRepository.findAll();
    }

    // READ BY ID
    public JobCard getById(Long id) {
        return jobCardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Job card not found"));
    }

    // UPDATE BASIC DETAILS
    public JobCard update(Long id, JobCard updated) {

        JobCard existing = getById(id);

        existing.setJobCardNumber(updated.getJobCardNumber());
        existing.setServiceDescription(updated.getServiceDescription());
        existing.setPartsCost(updated.getPartsCost());
        existing.setLabourCharges(updated.getLabourCharges());

        return jobCardRepository.save(existing);
    }

    // DELETE
    public void delete(Long id) {

        if (!jobCardRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Job card not found");
        }

        jobCardRepository.deleteById(id);
    }

    // ASSIGN BAY + MECHANIC
    @Transactional
    public JobCard assign(Long jobCardId, Long bayId, Long mechanicId) {

        JobCard jobCard = getById(jobCardId);

        Bay bay = bayRepository.findById(bayId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Bay not found"));

        Mechanic mechanic = mechanicRepository.findById(mechanicId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Mechanic not found"));

        if (bay.isOccupied()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Bay is already occupied");
        }

        if (!mechanic.isAvailable()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Mechanic is currently unavailable");
        }

        bay.setOccupied(true);
        mechanic.setAvailable(false);

        bayRepository.save(bay);
        mechanicRepository.save(mechanic);

        jobCard.setBay(bay);
        jobCard.setMechanic(mechanic);
        jobCard.setJobStatus(JobStatus.ASSIGNED);

        return jobCardRepository.save(jobCard);
    }

    // UPDATE STATUS
    @Transactional
    public JobCard updateStatus(Long id, JobStatus status) {

        JobCard jobCard = getById(id);

        jobCard.setJobStatus(status);

        // Free bay and mechanic when job is completed/cancelled
        if (status == JobStatus.COMPLETED ||
            status == JobStatus.CANCELLED) {

            if (jobCard.getBay() != null) {
                Bay bay = jobCard.getBay();
                bay.setOccupied(false);
                bayRepository.save(bay);
            }

            if (jobCard.getMechanic() != null) {
                Mechanic mechanic = jobCard.getMechanic();
                mechanic.setAvailable(true);
                mechanicRepository.save(mechanic);
            }
        }

        return jobCardRepository.save(jobCard);
    }
}