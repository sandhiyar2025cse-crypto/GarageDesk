package com.example.GarageDesk.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.GarageDesk.entity.Bill;
import com.example.GarageDesk.entity.JobCard;
import com.example.GarageDesk.entity.JobStatus;
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

    // Generate final bill
    public Bill generateBill(Long jobCardId) {

        JobCard jobCard = jobCardRepository.findById(jobCardId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job card not found"
                ));

        // Bill can be generated only after completion
        if (jobCard.getJobStatus() != JobStatus.COMPLETED) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Bill can be generated only for a completed job"
            );
        }

        // Prevent duplicate bill
        if (billRepository.findByJobCardId(jobCardId).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Bill already exists for this job card"
            );
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
                        "Bill not found"
                ));
    }

    // Delete bill
    public void delete(Long id) {

        if (!billRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Bill not found"
            );
        }

        billRepository.deleteById(id);
    }
}