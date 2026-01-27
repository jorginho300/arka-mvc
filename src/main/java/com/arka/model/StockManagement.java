package com.arka.model;

import java.time.LocalDate;

import com.arka.enums.StockManagementEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StockManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status", nullable = false)
	private StockManagementEnum status;
	@Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
	private LocalDate createdAt;
	@Column(name="quantity", nullable = false)
	private int quantity;
	@Column(name = "productId", nullable = false)
	private Long productId;
	
	public StockManagement() {
		
	}
	
	public StockManagement(Long id, StockManagementEnum status, LocalDate createdAt, int quantity, Long productid) {
		this.id = id;
		this.status = status;
		this.createdAt = createdAt;
		this.quantity = quantity;
		this.productId = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public StockManagementEnum getStatus() {
		return status;
	}
	public void setStatus(StockManagementEnum status) {
		this.status = status;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
