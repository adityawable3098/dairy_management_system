package com.milk.dto;

public class MonthlySummaryDTO {

    private String farmerName;
    private Double totalMilkLiters;
    private Double totalMilkAmount;
    private Double totalDeductions;
    private Double netPayable;
    private String farmerPhone;

    // Default constructor
    public MonthlySummaryDTO() {}

    // Parameterized constructor
    public MonthlySummaryDTO(String farmerName, Double totalMilkLiters, Double totalMilkAmount,
                             Double totalDeductions, Double netPayable, String farmerPhone) {

        this.farmerName = farmerName;
        this.totalMilkLiters = totalMilkLiters;
        this.totalMilkAmount = totalMilkAmount;
        this.totalDeductions = totalDeductions;
        this.netPayable = netPayable;
        this.farmerPhone = farmerPhone;
    }

    // Getters & Setters

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public Double getTotalMilkLiters() {
        return totalMilkLiters;
    }

    public void setTotalMilkLiters(Double totalMilkLiters) {
        this.totalMilkLiters = totalMilkLiters;
    }

    public Double getTotalMilkAmount() {
        return totalMilkAmount;
    }

    public void setTotalMilkAmount(Double totalMilkAmount) {
        this.totalMilkAmount = totalMilkAmount;
    }

    public Double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(Double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public Double getNetPayable() {
        return netPayable;
    }

    public void setNetPayable(Double netPayable) {
        this.netPayable = netPayable;
    }

    public String getFarmerPhone() {
        return farmerPhone;
    }

    public void setFarmerPhone(String farmerPhone) {
        this.farmerPhone = farmerPhone;
    }
}
