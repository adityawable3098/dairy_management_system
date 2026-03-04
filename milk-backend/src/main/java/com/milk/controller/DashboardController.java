package com.milk.controller;

import com.milk.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Simple DTO for dashboard data
class DashboardSummary {
    private long totalFarmers;
    private double todayMilk;
    

    public DashboardSummary(long totalFarmers, double todayMilk) {
        this.totalFarmers = totalFarmers;
        this.todayMilk = todayMilk;
        
    }

    public long getTotalFarmers() { return totalFarmers; }
    public double getTodayMilk() { return todayMilk; }
   
}

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")  // your Vite frontend
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public DashboardSummary getSummary() {
        long totalFarmers = dashboardService.getTotalFarmers();
        double todayMilk = dashboardService.getTodayMilk();
        

        return new DashboardSummary(totalFarmers, todayMilk);
    }
}
