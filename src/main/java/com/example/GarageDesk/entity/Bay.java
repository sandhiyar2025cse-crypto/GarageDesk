package com.example.GarageDesk.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Bay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bay number is required")
    private String bayNumber;

    @NotBlank(message = "Bay type is required")
    private String bayType;

    private boolean occupied = false;

    public Bay() {
    }

    public Bay(String bayNumber, String bayType, boolean occupied) {
        this.bayNumber = bayNumber;
        this.bayType = bayType;
        this.occupied = occupied;
    }

    public Long getId() {
        return id;
    }

    public String getBayNumber() {
        return bayNumber;
    }

    public void setBayNumber(String bayNumber) {
        this.bayNumber = bayNumber;
    }

    public String getBayType() {
        return bayType;
    }

    public void setBayType(String bayType) {
        this.bayType = bayType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}