package com.milk.dto;

import java.time.LocalDate;

public class MilkEntryDTO {

    private Integer collectionId;
    private Integer farmerId;
    private String farmerName;  
    private LocalDate entryDate;
    private String shift;
    private String milkType;

    private Double fat;
    private Double snf;
    private Double litres;
    private Double rate;
    private Double amount;
    private String farmerPhone;

    // Default constructor
    public MilkEntryDTO() {}

    // Parameterized constructor
    public MilkEntryDTO(Integer collectionId, Integer farmerId, String farmerName,
                        LocalDate entryDate, String shift, String milkType,
                        Double fat, Double snf, Double litres, Double rate, Double amount) {

        this.collectionId = collectionId;
        this.farmerId = farmerId;
        this.farmerName = farmerName;
        this.entryDate = entryDate;
        this.shift = shift;
        this.milkType = milkType;
        this.fat = fat;
        this.snf = snf;
        this.litres = litres;
        this.rate = rate;
        this.amount = amount;
    }

    // Getters & Setters

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Integer farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getMilkType() {
        return milkType;
    }

    public void setMilkType(String milkType) {
        this.milkType = milkType;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getSnf() {
        return snf;
    }

    public void setSnf(Double snf) {
        this.snf = snf;
    }

    public Double getLitres() {
        return litres;
    }

    public void setLitres(Double litres) {
        this.litres = litres;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFarmerPhone() {
        return farmerPhone;
    }

    public void setFarmerPhone(String farmerPhone) {
        this.farmerPhone = farmerPhone;
    }
}
