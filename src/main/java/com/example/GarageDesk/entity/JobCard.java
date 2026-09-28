package com.example.GarageDesk.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class JobCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Job card number is required")
    @Column(unique = true, nullable = false)
    private String jobCardNumber;

    @NotBlank(message = "Service description is required")
    private String serviceDescription;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus = JobStatus.CREATED;

    // Business rule: false until quality check is completed
    private boolean qualityChecked = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    @PositiveOrZero(message = "Parts cost cannot be negative")
    private BigDecimal partsCost = BigDecimal.ZERO;

    @PositiveOrZero(message = "Labour charges cannot be negative")
    private BigDecimal labourCharges = BigDecimal.ZERO;

    @NotNull(message = "Vehicle is required")
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(name = "bay_id")
    private Bay bay;

    // Default constructor required by JPA
    public JobCard() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getJobCardNumber() {
        return jobCardNumber;
    }

    public void setJobCardNumber(String jobCardNumber) {
        this.jobCardNumber = jobCardNumber;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public boolean isQualityChecked() {
        return qualityChecked;
    }

    public void setQualityChecked(boolean qualityChecked) {
        this.qualityChecked = qualityChecked;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public BigDecimal getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(BigDecimal partsCost) {
        this.partsCost = partsCost;
    }

    public BigDecimal getLabourCharges() {
        return labourCharges;
    }

    public void setLabourCharges(BigDecimal labourCharges) {
        this.labourCharges = labourCharges;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Bay getBay() {
        return bay;
    }

    public void setBay(Bay bay) {
        this.bay = bay;
    }
}