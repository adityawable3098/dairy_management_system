package com.milk.service;

import com.milk.entity.Farmer;

import java.util.List;
import java.util.Optional;

public interface FarmerService {
    Farmer save(Farmer farmer);
    List<Farmer> findAll();
    Optional<Farmer> findById(Integer id);
    void deleteById(Integer id);
}
