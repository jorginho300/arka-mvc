package com.arka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
