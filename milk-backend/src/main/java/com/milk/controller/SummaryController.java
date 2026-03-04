package com.milk.controller;

import com.milk.dto.MonthlySummaryResponse;
import com.milk.service.SummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("/monthly/{farmerId}/{year}/{month}")
    public ResponseEntity<MonthlySummaryResponse> getMonthlySummary(
            @PathVariable Integer farmerId,
            @PathVariable Integer year,
            @PathVariable Integer month) {

        MonthlySummaryResponse summary = summaryService.getMonthlySummary(farmerId, year, month);

        if (summary == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(summary);
    }
}
