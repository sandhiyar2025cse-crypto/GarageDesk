package com.example.GarageDesk.controller;

import com.example.GarageDesk.entity.Bill;
import com.example.GarageDesk.service.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService service;

    public BillController(BillService service) {
        this.service = service;
    }

    // Generate final bill
    @PostMapping("/generate/{jobCardId}")
    public Bill generateBill(@PathVariable Long jobCardId) {
        return service.generateBill(jobCardId);
    }

    @GetMapping
    public List<Bill> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Bill getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Bill deleted successfully";
    }
}