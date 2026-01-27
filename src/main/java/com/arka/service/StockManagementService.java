package com.arka.service;

import com.arka.model.StockManagement;

public interface StockManagementService {
	StockManagement addStock(Long productId, int amount);
}
