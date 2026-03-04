package com.milk.service;

import com.milk.entity.Farmer;
import com.milk.repository.FarmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerServiceImpl(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Override
    public Farmer save(Farmer farmer) {
        // avoid nulls for new farmers
        if (farmer.getTotalMilk() == null) farmer.setTotalMilk(0.0);
        if (farmer.getTotalPrice() == null) farmer.setTotalPrice(0.0);
        if (farmer.getDeductions() == null) farmer.setDeductions(0.0);
        if (farmer.getNetPayable() == null) {
            farmer.setNetPayable(farmer.getTotalPrice() - farmer.getDeductions());
        }
        return farmerRepository.save(farmer);
    }

    @Override
    public List<Farmer> findAll() {
        // ❗ just read what is already stored in DB
        return farmerRepository.findAll();
    }

    @Override
    public Optional<Farmer> findById(Integer id) {
        return farmerRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        farmerRepository.deleteById(id);
    }
}
