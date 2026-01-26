package com.arka.service;

import org.springframework.stereotype.Service;

import com.arka.dto.ProductRequest;
import com.arka.model.Category;
import com.arka.model.Product;
import com.arka.repository.CategoryRepository;
import com.arka.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Product createProduct(ProductRequest request) {
		Category category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category doesn't exists"));
		
		Product product = new Product();
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		product.setDescription(request.getDescription());
		product.setCategory(category);
		product.setActive(true);
		product.setStock(0);
		
		return productRepository.save(product);
	}

}
