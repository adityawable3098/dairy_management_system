package com.milk.service;

import com.milk.entity.Deduction;
import com.milk.entity.Farmer;
import com.milk.repository.DeductionRepository;
import com.milk.repository.FarmerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Service
public class DeductionServiceImpl implements DeductionService {

    private final DeductionRepository deductionRepository;
    private final FarmerRepository farmerRepository;

    public DeductionServiceImpl(DeductionRepository deductionRepository,
                                FarmerRepository farmerRepository) {
        this.deductionRepository = deductionRepository;
        this.farmerRepository = farmerRepository;
    }

    @Override
    @Transactional
    public Deduction save(Deduction deduction) {

        // ⭐ Fix: Auto assign deduction date if null
        if (deduction.getDeductionDate() == null) {
            deduction.setDeductionDate(LocalDate.now());
        }

        // 1️⃣ Save deduction record
        Deduction saved = deductionRepository.save(deduction);

        // 2️⃣ Validate farmer
        Farmer farmer = saved.getFarmer();

        if (farmer == null || farmer.getFarmerId() == null) {
            throw new RuntimeException("Deduction must be linked to a valid farmer");
        }

        farmer = farmerRepository.findById(farmer.getFarmerId())
                .orElseThrow(() -> new RuntimeException("Invalid farmer"));

        // 3️⃣ Update farmer's total deduction
        double previousDeduction = farmer.getDeductions() != null ? farmer.getDeductions() : 0.0;
        double thisDeduction = saved.getAmount() != null ? saved.getAmount() : 0.0;

        farmer.setDeductions(previousDeduction + thisDeduction);

        // 4️⃣ Recalculate net payable
        double totalPrice = farmer.getTotalPrice() != null ? farmer.getTotalPrice() : 0.0;
        farmer.setNetPayable(totalPrice - farmer.getDeductions());

        // 5️⃣ Save updated farmer
        farmerRepository.save(farmer);

        return saved;
    }

    @Override
    public Optional<Deduction> findById(Integer id) {
        return deductionRepository.findById(id);
    }

    @Override
    public List<Deduction> findByFarmerId(Integer farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        if (farmer == null) return List.of();
        return deductionRepository.findByFarmer(farmer);
    }

    @Override
    public List<Deduction> findByDateRange(LocalDate start, LocalDate end) {
        return deductionRepository.findByDeductionDateBetween(start, end);
    }
}
