package com.milk.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "milk_entry")
public class MilkEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    private LocalDate entryDate;
    private String shift;       // "morning" / "evening"
    private String milkType;    // "cow" / "buffalo"

    private Double fat;
    private Double snf;
    private Double litres;
    private Double rate;
    private Double amount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Empty Constructor (Required by JPA)
    public MilkEntry() {
    }

    // Parameterized Constructor
    public MilkEntry(Integer collectionId, Farmer farmer, LocalDate entryDate, String shift, String milkType,
                     Double fat, Double snf, Double litres, Double rate, Double amount, LocalDateTime createdAt) {
        this.collectionId = collectionId;
        this.farmer = farmer;
        this.entryDate = entryDate;
        this.shift = shift;
        this.milkType = milkType;
        this.fat = fat;
        this.snf = snf;
        this.litres = litres;
        this.rate = rate;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
