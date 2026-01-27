package com.arka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.model.StockManagement;

public interface StockManagementRepository extends JpaRepository<StockManagement, Long> {
	
}
