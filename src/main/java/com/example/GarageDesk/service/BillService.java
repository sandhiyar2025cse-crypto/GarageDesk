package com.example.GarageDesk.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GarageDesk.entity.Bill;
import com.example.GarageDesk.entity.JobCard;
import com.example.GarageDesk.repository.BillRepository;
import com.example.GarageDesk.repository.JobCardRepository;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final JobCardRepository jobCardRepository;

    public BillService(
            BillRepository billRepository,
            JobCardRepository jobCardRepository) {

        this.billRepository = billRepository;
        this.jobCardRepository = jobCardRepository;
    }

    // Generate final bill from job card
    public Bill generateBill(Long jobCardId) {

        JobCard jobCard = jobCardRepository.findById(jobCardId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job card not found"));

        if (jobCard.getJobStatus().name().equals("CANCELLED")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot generate bill for cancelled job");
        }

        Bill bill = new Bill();

        bill.setBillNumber("BILL-" + System.currentTimeMillis());

        bill.setPartsCost(jobCard.getPartsCost());

        bill.setLabourCost(jobCard.getLabourCharges());

        BigDecimal totalAmount =
                jobCard.getPartsCost()
                        .add(jobCard.getLabourCharges());

        bill.setTotalAmount(totalAmount);

        bill.setJobCard(jobCard);

        return billRepository.save(bill);
    }

    // Get all bills
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    // Get bill by ID
    public Bill getById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Bill not found"));
    }

    // Delete bill
    public void delete(Long id) {

        if (!billRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Bill not found");
        }

        billRepository.deleteById(id);
    }
}