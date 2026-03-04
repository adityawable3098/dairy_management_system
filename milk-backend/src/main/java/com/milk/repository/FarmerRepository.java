package com.milk.repository;

import com.milk.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
	 @Query("SELECT COUNT(f) FROM Farmer f")
	    long getTotalFarmers();
}
