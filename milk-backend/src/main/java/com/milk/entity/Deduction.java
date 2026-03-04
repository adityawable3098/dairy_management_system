package com.milk.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "deduction")
public class Deduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deductionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    private LocalDate deductionDate;
    private String type;       // feed / advance / other
    private Double amount;
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Empty constructor (required by JPA)
    public Deduction() {
    }

    // Parameterized constructor
    public Deduction(Integer deductionId, Farmer farmer, LocalDate deductionDate,
                     String type, Double amount, String notes, LocalDateTime createdAt) {
        this.deductionId = deductionId;
        this.farmer = farmer;
        this.deductionDate = deductionDate;
        this.type = type;
        this.amount = amount;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        // assign date if UI didn’t send
        if (deductionDate == null) {
            deductionDate = LocalDate.now();
        }
    }

    // Getters and Setters

    public Integer getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(Integer deductionId) {
        this.deductionId = deductionId;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public LocalDate getDeductionDate() {
        return deductionDate;
    }

    public void setDeductionDate(LocalDate deductionDate) {
        this.deductionDate = deductionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
