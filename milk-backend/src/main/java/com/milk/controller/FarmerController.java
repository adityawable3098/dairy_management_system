package com.milk.controller;

import com.milk.entity.Farmer;
import com.milk.service.FarmerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    // Fetch all farmers
    @GetMapping
    public ResponseEntity<List<Farmer>> all() {
        return ResponseEntity.ok(farmerService.findAll());
    }

    // Create new farmer
    @PostMapping
    public ResponseEntity<Farmer> create(@RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.save(farmer));
    }

    // Get farmer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Farmer> get(@PathVariable Integer id) {
        return farmerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update farmer record (needed for milk update + deductions update)
    @PutMapping("/{id}")
    public ResponseEntity<Farmer> update(@PathVariable Integer id, @RequestBody Farmer updated) {
        return farmerService.findById(id)
                .map(existing -> {

                    existing.setName(updated.getName());
                    existing.setVillage(updated.getVillage());
                    existing.setPhone(updated.getPhone());

                    // Updating new financial/milk fields
                    existing.setTotalMilk(updated.getTotalMilk());
                    existing.setTotalPrice(updated.getTotalPrice());
                    existing.setDeductions(updated.getDeductions());

                    // Recalculate net payable if not manually set
                    if (updated.getNetPayable() != null) {
                        existing.setNetPayable(updated.getNetPayable());
                    } else {
                        existing.setNetPayable(existing.getTotalPrice() - existing.getDeductions());
                    }

                    return ResponseEntity.ok(farmerService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
}
