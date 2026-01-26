package com.arka.dto;

import java.math.BigDecimal;

public class ProductRequest {
	private String name;
	private BigDecimal price;
	private boolean active;
	private Integer stock;
	private Long categoryId;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public ProductRequest(String name, BigDecimal price, Long categoryId, String description) {
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
	}
	
	
}
