package com.arka.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.arka.enums.StockManagementEnum;
import com.arka.model.Product;
import com.arka.model.StockManagement;
import com.arka.repository.ProductRepository;
import com.arka.repository.StockManagementRepository;

@Service
public class StockManagementServiceImpl implements StockManagementService {
	
	private final ProductRepository productRepository;
	private final StockManagementRepository stockRepository;
	
	public StockManagementServiceImpl(ProductRepository productRepository, StockManagementRepository stockRepository) {
		this.productRepository = productRepository;
		this.stockRepository = stockRepository;
	}

	@Override
	public StockManagement addStock(Long productId, int amount) {
		if(amount > 0) {
			Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
			product.setStock(amount);
			
			StockManagement stock = new StockManagement();
			stock.setCreatedAt(LocalDate.now());
			stock.setStatus(StockManagementEnum.APPROVED);
			stock.setProductId(productId);
			stock.setQuantity(amount);
			return stockRepository.save(stock);
		} else {
			throw new UnsupportedOperationException("Amount must be greater than zero");
		}
		

	}

}
