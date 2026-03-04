package com.milk.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DeductionDTO {

    private Integer deductionId;
    private Integer farmerId;
    private String type;
    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deductionDate;

    private String notes;

    public DeductionDTO() {}

    public DeductionDTO(Integer deductionId, Integer farmerId, String type,
                        Double amount, LocalDate deductionDate, String notes) {
        this.deductionId = deductionId;
        this.farmerId = farmerId;
        this.type = type;
        this.amount = amount;
        this.deductionDate = deductionDate;
        this.notes = notes;
    }

    public Integer getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(Integer deductionId) {
        this.deductionId = deductionId;
    }

    public Integer getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Integer farmerId) {
        this.farmerId = farmerId;
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

    public LocalDate getDeductionDate() {
        return deductionDate;
    }

    public void setDeductionDate(LocalDate deductionDate) {
        this.deductionDate = deductionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
