package com.milk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.milk.repository.*;
import com.milk.repository.FarmerRepository;

@Service
public class DashboardService {

    @Autowired
    private FarmerRepository farmerRepo;

    @Autowired
    private MilkEntryRepository milkRepo;

   

   

    public long getTotalFarmers() {
        return farmerRepo.getTotalFarmers();
    }

    public double getTodayMilk() {
        return milkRepo.getTodayMilk();
    }

  
   
    
}
