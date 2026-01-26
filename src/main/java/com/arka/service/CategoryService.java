package com.arka.service;

import java.util.ArrayList;

import com.arka.model.Category;

public interface CategoryService {
	ArrayList<Category> listCategories();
	Category saveCategory (Category category);
}
