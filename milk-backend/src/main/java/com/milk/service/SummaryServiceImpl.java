package com.milk.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.milk.dto.MilkEntryDTO;
import com.milk.dto.MonthlySummaryResponse;
import com.milk.entity.MilkEntry;
import com.milk.repository.DeductionRepository;
import com.milk.repository.MilkEntryRepository;

@Service
public class SummaryServiceImpl implements SummaryService {

    private final MilkEntryRepository milkEntryRepository;
    private final DeductionRepository deductionRepository;

    public SummaryServiceImpl(MilkEntryRepository milkEntryRepository,
                              DeductionRepository deductionRepository) {
        this.milkEntryRepository = milkEntryRepository;
        this.deductionRepository = deductionRepository;
    }

    @Override
    public MonthlySummaryResponse getMonthlySummary(Integer farmerId, Integer year, Integer month) {

        MonthlySummaryResponse response = new MonthlySummaryResponse();

        List<MilkEntry> milkEntries = milkEntryRepository.findMonthlyMilk(farmerId, year, month);

        // Convert milk entries
        List<MilkEntryDTO> dtos = milkEntries.stream().map(e -> {
            MilkEntryDTO dto = new MilkEntryDTO();
            dto.setCollectionId(e.getCollectionId());
            dto.setFarmerId(e.getFarmer().getFarmerId());
            dto.setFarmerName(e.getFarmer().getName());
            dto.setFarmerPhone(e.getFarmer().getPhone());
            dto.setEntryDate(e.getEntryDate());
            dto.setShift(e.getShift());
            dto.setMilkType(e.getMilkType());
            dto.setFat(e.getFat());
            dto.setSnf(e.getSnf());
            dto.setLitres(e.getLitres());
            dto.setRate(e.getRate());
            dto.setAmount(e.getAmount());
            return dto;
        }).toList();

        response.setRecords(dtos);

        //  Totals
        double totalLitres = milkEntries.stream()
                .mapToDouble(m -> m.getLitres() != null ? m.getLitres() : 0.0).sum();

        double totalAmount = milkEntries.stream()
                .mapToDouble(m -> m.getAmount() != null ? m.getAmount() : 0.0).sum();

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Double totalDeduction = deductionRepository.sumByFarmerMonth(farmerId, year, month);

        if (totalDeduction == null) totalDeduction = 0.0;

        double netPayable = totalAmount - totalDeduction;

        response.setTotalLitres(totalLitres);
        response.setTotalAmount(totalAmount);
        response.setTotalDeduction(totalDeduction);
        response.setNetPayable(netPayable);

        return response;
    }
}
