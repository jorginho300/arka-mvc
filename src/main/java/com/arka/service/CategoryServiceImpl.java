package com.arka.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arka.model.Category;
import com.arka.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ArrayList<Category> listCategories() {
		// TODO Auto-generated method stub
		return (ArrayList<Category>) categoryRepository.findAll();
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

}
