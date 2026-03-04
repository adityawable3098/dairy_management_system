package com.milk.dto;

import java.util.List;

public class MonthlySummaryResponse {

    private List<MilkEntryDTO> records;

    private double totalLitres;
    private double totalAmount;
    private double totalDeduction;
    private double netPayable;

    // Default constructor
    public MonthlySummaryResponse() {}

    // Parameterized constructor
    public MonthlySummaryResponse(List<MilkEntryDTO> records,
                                  double totalLitres,
                                  double totalAmount,
                                  double totalDeduction,
                                  double netPayable) {

        this.records = records;
        this.totalLitres = totalLitres;
        this.totalAmount = totalAmount;
        this.totalDeduction = totalDeduction;
        this.netPayable = netPayable;
    }

    // Getters & Setters

    public List<MilkEntryDTO> getRecords() {
        return records;
    }

    public void setRecords(List<MilkEntryDTO> records) {
        this.records = records;
    }

    public double getTotalLitres() {
        return totalLitres;
    }

    public void setTotalLitres(double totalLitres) {
        this.totalLitres = totalLitres;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(double totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public double getNetPayable() {
        return netPayable;
    }

    public void setNetPayable(double netPayable) {
        this.netPayable = netPayable;
    }
}
