package com.milk.repository;

import com.milk.entity.Deduction;
import com.milk.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeductionRepository extends JpaRepository<Deduction, Integer> {

    List<Deduction> findByFarmer(Farmer farmer);

    List<Deduction> findByDeductionDateBetween(LocalDate start, LocalDate end);

    @Query("""
           SELECT d FROM Deduction d
           WHERE d.farmer.farmerId = :farmerId
             AND d.deductionDate BETWEEN :start AND :end
           """)
    List<Deduction> findByFarmerIdAndDateRange(
            @Param("farmerId") Integer farmerId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    @Query("""
           SELECT COALESCE(SUM(d.amount), 0)
           FROM Deduction d
           WHERE d.farmer.farmerId = :farmerId
             AND FUNCTION('YEAR', d.deductionDate) = :year
             AND FUNCTION('MONTH', d.deductionDate) = :month
           """)
    Double sumByFarmerMonth(
            @Param("farmerId") Integer farmerId,
            @Param("year") Integer year,
            @Param("month") Integer month
    );
}
