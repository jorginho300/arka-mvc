package com.arka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arka.model.StockManagement;
import com.arka.service.StockManagementService;

@RestController
@RequestMapping("/apiarka/supply")
public class StockManagementController {
	
	private final StockManagementService stockService;
	
	public StockManagementController(StockManagementService stockService) {
		this.stockService = stockService;
	}
	
	@PostMapping("/{productId}/")
	public ResponseEntity<StockManagement> supply(@PathVariable Long productId, @RequestParam("stock") int amount) {
		StockManagement stock = stockService.addStock(productId, amount);
		return ResponseEntity.status(HttpStatus.CREATED).body(stock);
	}

}
