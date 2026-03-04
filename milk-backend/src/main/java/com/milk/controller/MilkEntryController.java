package com.milk.controller;

import com.milk.dto.MilkEntryDTO;
import com.milk.entity.Farmer;
import com.milk.entity.MilkEntry;
import com.milk.repository.FarmerRepository;
import com.milk.service.MilkEntryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/milk")
public class MilkEntryController {

    private final MilkEntryService milkEntryService;
    private final FarmerRepository farmerRepository;

    public MilkEntryController(MilkEntryService milkEntryService, FarmerRepository farmerRepository) {
        this.milkEntryService = milkEntryService;
        this.farmerRepository = farmerRepository;
    }

    @PostMapping
    public ResponseEntity<MilkEntryDTO> add(@RequestBody MilkEntryDTO dto) {

        Farmer farmer = farmerRepository.findById(dto.getFarmerId()).orElse(null);
        if (farmer == null) return ResponseEntity.badRequest().build();

        MilkEntry m = new MilkEntry();
        m.setFarmer(farmer);
        m.setEntryDate(dto.getEntryDate());
        m.setShift(dto.getShift());
        m.setMilkType(dto.getMilkType());
        m.setFat(dto.getFat());
        m.setSnf(dto.getSnf());
        m.setLitres(dto.getLitres());
        m.setRate(dto.getRate());
        m.setAmount(dto.getAmount());

        MilkEntry saved = milkEntryService.save(m);

        dto.setCollectionId(saved.getCollectionId());
        dto.setFarmerName(farmer.getName());   // ⭐ return name

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<MilkEntryDTO>> byDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<MilkEntry> list = milkEntryService.findByDate(date);

        List<MilkEntryDTO> dtos = list.stream().map(m -> {

            MilkEntryDTO dto = new MilkEntryDTO();
            dto.setCollectionId(m.getCollectionId());
            dto.setFarmerId(m.getFarmer().getFarmerId());
            dto.setFarmerName(m.getFarmer().getName()); // ⭐ added
            dto.setEntryDate(m.getEntryDate());
            dto.setShift(m.getShift());
            dto.setMilkType(m.getMilkType());
            dto.setFat(m.getFat());
            dto.setSnf(m.getSnf());
            dto.setLitres(m.getLitres());
            dto.setRate(m.getRate());
            dto.setAmount(m.getAmount());

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<MilkEntryDTO>> byFarmer(@PathVariable Integer farmerId) {

        // ⭐ Use new service DTO method instead of manual mapping
        return ResponseEntity.ok(milkEntryService.getDTOByFarmer(farmerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilkEntryDTO> get(@PathVariable Integer id) {
        return milkEntryService.findById(id)
                .map(m -> {
                    MilkEntryDTO dto = new MilkEntryDTO();
                    dto.setCollectionId(m.getCollectionId());
                    dto.setFarmerId(m.getFarmer().getFarmerId());
                    dto.setFarmerName(m.getFarmer().getName()); // ⭐ added
                    dto.setEntryDate(m.getEntryDate());
                    dto.setShift(m.getShift());
                    dto.setMilkType(m.getMilkType());
                    dto.setFat(m.getFat());
                    dto.setSnf(m.getSnf());
                    dto.setLitres(m.getLitres());
                    dto.setRate(m.getRate());
                    dto.setAmount(m.getAmount());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/summary/{farmerId}/{year}/{month}")
    public ResponseEntity<?> getMonthlySummary(
            @PathVariable Integer farmerId,
            @PathVariable Integer year,
            @PathVariable Integer month) {

        // 1️⃣ Validate farmer
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer == null) {
            return ResponseEntity.badRequest().body("Invalid Farmer ID");
        }

        // 2️⃣ Define date range
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // 3️⃣ Fetch entries for given farmer & month
        List<MilkEntry> entries = milkEntryService.findByFarmerAndDateRange(
                farmerId, startDate, endDate);

        // 4️⃣ Convert to DTO list
        List<MilkEntryDTO> dtos = entries.stream().map(m -> {
            MilkEntryDTO dto = new MilkEntryDTO();
            dto.setCollectionId(m.getCollectionId());
            dto.setFarmerId(farmerId);
            dto.setFarmerName(farmer.getName());
            dto.setEntryDate(m.getEntryDate());
            dto.setShift(m.getShift());
            dto.setMilkType(m.getMilkType());
            dto.setFat(m.getFat());
            dto.setSnf(m.getSnf());
            dto.setLitres(m.getLitres());
            dto.setRate(m.getRate());
            dto.setAmount(m.getAmount());
            return dto;
        }).collect(Collectors.toList());

        // 5️⃣ Calculate totals
        double totalLitres = entries.stream()
                .mapToDouble(e -> e.getLitres() != null ? e.getLitres() : 0.0)
                .sum();

        double totalAmount = entries.stream()
                .mapToDouble(e -> e.getAmount() != null ? e.getAmount() : 0.0)
                .sum();

        double totalDeduction = farmer.getDeductions() != null ? farmer.getDeductions() : 0.0;

        double netPayable = totalAmount - totalDeduction;

        // 6️⃣ Return structured result
        return ResponseEntity.ok(
                java.util.Map.of(
                        "farmerName", farmer.getName(),
                        "entries", dtos,
                        "totalLitres", totalLitres,
                        "totalAmount", totalAmount,
                        "totalDeduction", totalDeduction,
                        "netPayable", netPayable
                )
        );
    }

}
