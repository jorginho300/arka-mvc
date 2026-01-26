package com.arka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.dto.ProductRequest;
import com.arka.model.Product;
import com.arka.service.ProductService;

@RestController
@RequestMapping("/apiarka/products")
public class ProductController {
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Product> create(@RequestBody ProductRequest request) {
		Product product = productService.createProduct(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	
}
