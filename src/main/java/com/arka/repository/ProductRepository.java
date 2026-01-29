package com.arka.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arka.model.Product;

import jakarta.persistence.LockModeType;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT p from Product p where p.id = :id")
	Optional<Product> findByIdWithLock(@Param("id") Long id);
}
