package com.milk.repository;

import com.milk.entity.MilkEntry;
import com.milk.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MilkEntryRepository extends JpaRepository<MilkEntry, Integer> {

    List<MilkEntry> findByEntryDate(LocalDate date);

    List<MilkEntry> findByFarmer(Farmer farmer);

    List<MilkEntry> findByFarmerAndEntryDateBetween(Farmer farmer, LocalDate start, LocalDate end);

    @Query("""
           SELECT m FROM MilkEntry m
           WHERE m.farmer.farmerId = :farmerId
             AND m.entryDate BETWEEN :start AND :end
           """)
    List<MilkEntry> findByFarmerIdAndDateRange(@Param("farmerId") Integer farmerId,
                                               @Param("start") LocalDate start,
                                               @Param("end") LocalDate end);

    // Derived query
    List<MilkEntry> findByFarmerFarmerId(Integer farmerId);

    @Query("""
           SELECT m FROM MilkEntry m
           WHERE m.farmer.farmerId = :farmerId
             AND FUNCTION('YEAR', m.entryDate) = :year
             AND FUNCTION('MONTH', m.entryDate) = :month
           """)
    List<MilkEntry> findMonthlyMilk(@Param("farmerId") Integer farmerId,
                                    @Param("year") Integer year,
                                    @Param("month") Integer month);

    // ⭐ Final fixed method
    @Query("""
           SELECT COALESCE(SUM(m.litres), 0)
           FROM MilkEntry m
           WHERE m.entryDate = CURRENT_DATE
           """)
    double getTodayMilk();
}
