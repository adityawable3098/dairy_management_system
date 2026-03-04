package com.milk.service;

import com.milk.dto.MilkEntryDTO;
import com.milk.entity.Farmer;
import com.milk.entity.MilkEntry;
import com.milk.repository.FarmerRepository;
import com.milk.repository.MilkEntryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

@Service
public class MilkEntryServiceImpl implements MilkEntryService {

    @Autowired
    private FarmerRepository farmerRepository;

    private final MilkEntryRepository milkEntryRepository;

    public MilkEntryServiceImpl(MilkEntryRepository milkEntryRepository, FarmerRepository farmerRepository) {
        this.milkEntryRepository = milkEntryRepository;
        this.farmerRepository = farmerRepository;
    }

    @Override
    @Transactional
    public MilkEntry save(MilkEntry entry) {

        // Auto compute amount if missing
        if (entry.getAmount() == null && entry.getLitres() != null && entry.getRate() != null) {
            entry.setAmount(entry.getLitres() * entry.getRate());
        }

        // Save milk entry
        MilkEntry savedEntry = milkEntryRepository.save(entry);

        Farmer farmer = entry.getFarmer();

        if (farmer == null || farmer.getFarmerId() == null) {
            throw new RuntimeException("Milk entry must contain a valid farmer reference");
        }

        // Fetch farmer fresh
        farmer = farmerRepository.findById(farmer.getFarmerId())
                .orElseThrow(() -> new RuntimeException("Invalid farmer ID"));

        // Update farmer totals
        double litres = entry.getLitres() != null ? entry.getLitres() : 0.0;
        double rate = entry.getRate() != null ? entry.getRate() : 0.0;
        double totalAmount = litres * rate;

        farmer.setTotalMilk(
                (farmer.getTotalMilk() != null ? farmer.getTotalMilk() : 0.0) + litres
        );

        farmer.setTotalPrice(
                (farmer.getTotalPrice() != null ? farmer.getTotalPrice() : 0.0) + totalAmount
        );

        double deductions = farmer.getDeductions() != null ? farmer.getDeductions() : 0.0;
        farmer.setNetPayable(farmer.getTotalPrice() - deductions);

        farmerRepository.save(farmer);

        return savedEntry;
    }

    @Override
    public Optional<MilkEntry> findById(Integer id) {
        return milkEntryRepository.findById(id);
    }

    @Override
    public List<MilkEntry> findByDate(LocalDate date) {
        return milkEntryRepository.findByEntryDate(date);
    }

    @Override
    public List<MilkEntry> findByFarmerId(Integer farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer == null) return List.of();
        return milkEntryRepository.findByFarmer(farmer);
    }

    @Override
    public List<MilkEntry> findByFarmerAndDateRange(Integer farmerId, LocalDate start, LocalDate end) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer == null) return List.of();
        return milkEntryRepository.findByFarmerAndEntryDateBetween(farmer, start, end);
    }

    // ⭐ UPDATED METHOD — returns DTO including farmer name & phone
    @Override
    public List<MilkEntryDTO> getDTOByFarmer(Integer farmerId) {

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        return milkEntryRepository.findByFarmer(farmer)
                .stream()
                .map(entry -> {
                    MilkEntryDTO dto = new MilkEntryDTO(
                            entry.getCollectionId(),
                            farmer.getFarmerId(),
                            farmer.getName(),
                            entry.getEntryDate(),
                            entry.getShift(),
                            entry.getMilkType(),
                            entry.getFat(),
                            entry.getSnf(),
                            entry.getLitres(),
                            entry.getRate(),
                            entry.getAmount()
                    );

                    // ⭐ ADD PHONE IN DTO
                    dto.setFarmerPhone(farmer.getPhone());

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
