package com.milk.service;

import com.milk.dto.MonthlySummaryResponse;

public interface SummaryService {
    MonthlySummaryResponse getMonthlySummary(Integer farmerId, Integer year, Integer month);
}
