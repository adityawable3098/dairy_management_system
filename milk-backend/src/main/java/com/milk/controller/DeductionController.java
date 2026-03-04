package com.milk.controller;

import com.milk.dto.DeductionDTO;
import com.milk.entity.Deduction;
import com.milk.entity.Farmer;
import com.milk.repository.FarmerRepository;
import com.milk.service.DeductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/deductions")
public class DeductionController {

    private final DeductionService deductionService;
    private final FarmerRepository farmerRepository;

    public DeductionController(DeductionService deductionService, FarmerRepository farmerRepository) {
        this.deductionService = deductionService;
        this.farmerRepository = farmerRepository;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody DeductionDTO dto) {

        Farmer farmer = farmerRepository.findById(dto.getFarmerId())
                .orElse(null);

        if (farmer == null) {
            return ResponseEntity.badRequest().body("❌ Farmer not found");
        }

        Deduction deduction = new Deduction();
        deduction.setFarmer(farmer);

        deduction.setDeductionDate(dto.getDeductionDate() != null ?
                dto.getDeductionDate() : LocalDate.now());

        deduction.setType(dto.getType());
        deduction.setAmount(dto.getAmount());
        deduction.setNotes(dto.getNotes());

        Deduction saved = deductionService.save(deduction);

        // return filled DTO
        DeductionDTO response = new DeductionDTO();
        response.setDeductionId(saved.getDeductionId());
        response.setFarmerId(saved.getFarmer().getFarmerId());
        response.setDeductionDate(saved.getDeductionDate());
        response.setType(saved.getType());
        response.setAmount(saved.getAmount());
        response.setNotes(saved.getNotes());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<DeductionDTO>> byFarmer(@PathVariable Integer farmerId) {

        List<Deduction> list = deductionService.findByFarmerId(farmerId);

        List<DeductionDTO> dtos = list.stream()
                .map(d -> {
                    DeductionDTO dto = new DeductionDTO();
                    dto.setDeductionId(d.getDeductionId());
                    dto.setFarmerId(d.getFarmer().getFarmerId());
                    dto.setDeductionDate(d.getDeductionDate());
                    dto.setType(d.getType());
                    dto.setAmount(d.getAmount());
                    dto.setNotes(d.getNotes());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
