package com.arka.service;

import com.arka.dto.ProductRequest;
import com.arka.model.Product;

public interface ProductService {
	Product createProduct (ProductRequest request);
	
}
