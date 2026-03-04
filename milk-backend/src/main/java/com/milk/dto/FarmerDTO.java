package com.milk.dto;

public class FarmerDTO {

    private Integer farmerId;
    private String name;
    private String village;
    private String phone;

    private Double totalMilk = 0.0;
    private Double totalPrice = 0.0;
    private Double deductions = 0.0;
    private Double netPayable = 0.0;

    // Default constructor
    public FarmerDTO() {}

    // Parameterized constructor
    public FarmerDTO(Integer farmerId, String name, String village, String phone,
                     Double totalMilk, Double totalPrice, Double deductions, Double netPayable) {

        this.farmerId = farmerId;
        this.name = name;
        this.village = village;
        this.phone = phone;
        this.totalMilk = totalMilk;
        this.totalPrice = totalPrice;
        this.deductions = deductions;
        this.netPayable = netPayable;
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
}
