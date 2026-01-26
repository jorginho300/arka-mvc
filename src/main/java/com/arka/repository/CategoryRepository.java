package com.arka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
