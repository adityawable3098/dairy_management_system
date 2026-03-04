package com.milk.service;

import com.milk.dto.MilkEntryDTO;
import com.milk.entity.MilkEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MilkEntryService {
    MilkEntry save(MilkEntry entry);
    Optional<MilkEntry> findById(Integer id);
    List<MilkEntry> findByDate(LocalDate date);
    List<MilkEntry> findByFarmerId(Integer farmerId);
    List<MilkEntry> findByFarmerAndDateRange(Integer farmerId, LocalDate start, LocalDate end);
    List<MilkEntryDTO> getDTOByFarmer(Integer farmerId);

}
