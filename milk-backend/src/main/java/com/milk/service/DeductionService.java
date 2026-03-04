package com.milk.service;

import com.milk.entity.Deduction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DeductionService {
    Deduction save(Deduction deduction);
    Optional<Deduction> findById(Integer id);
    List<Deduction> findByFarmerId(Integer farmerId);
    List<Deduction> findByDateRange(LocalDate start, LocalDate end);
}
