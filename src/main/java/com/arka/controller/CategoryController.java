package com.arka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.model.Category;
import com.arka.service.CategoryService;

@RestController
@RequestMapping("/apiarka/categories")
public class CategoryController {
	
	@Autowired
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Category>> categories() {
		List<Category> categoryList = categoryService.listCategories();
		return ResponseEntity.ok().body(categoryList);
	}
}
