package com.example.GarageDesk.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String billNumber;

    @PositiveOrZero
    private BigDecimal partsCost = BigDecimal.ZERO;

    @PositiveOrZero
    private BigDecimal labourCost = BigDecimal.ZERO;

    private BigDecimal totalAmount;

    private LocalDateTime generatedDate = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "job_card_id", unique = true)
    private JobCard jobCard;

    public Bill() {
    }

    public Long getId() {
        return id;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public BigDecimal getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(BigDecimal partsCost) {
        this.partsCost = partsCost;
    }

    public BigDecimal getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(BigDecimal labourCost) {
        this.labourCost = labourCost;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getGeneratedDate() {
        return generatedDate;
    }

    public JobCard getJobCard() {
        return jobCard;
    }

    public void setJobCard(JobCard jobCard) {
        this.jobCard = jobCard;
    }
}