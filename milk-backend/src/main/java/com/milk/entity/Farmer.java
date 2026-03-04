package com.milk.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "farmer")
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer farmerId;

    private String name;
    private String village;
    private String phone;

    @Column(name = "total_milk")
    private Double totalMilk = 0.0;

    @Column(name = "total_price")
    private Double totalPrice = 0.0;

    @Column(name = "deductions")
    private Double deductions = 0.0;

    @Column(name = "net_payable")
    private Double netPayable = 0.0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Required by JPA (must exist)
    public Farmer() {}

    // Full constructor
    public Farmer(Integer farmerId, String name, String village, String phone,
                  Double totalMilk, Double totalPrice, Double deductions, Double netPayable,
                  LocalDateTime createdAt) {
        this.farmerId = farmerId;
        this.name = name;
        this.village = village;
        this.phone = phone;
        this.totalMilk = totalMilk;
        this.totalPrice = totalPrice;
        this.deductions = deductions;
        this.netPayable = netPayable;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters & Setters

    public Integer getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Integer farmerId) {
        this.farmerId = farmerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTotalMilk() {
        return totalMilk;
    }

    public void setTotalMilk(Double totalMilk) {
        this.totalMilk = totalMilk;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public Double getNetPayable() {
        return netPayable;
    }

    public void setNetPayable(Double netPayable) {
        this.netPayable = netPayable;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
