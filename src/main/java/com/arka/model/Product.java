package com.arka.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	@Column(name = "active", nullable = false)
	private boolean active;
	@Column(name = "stock", nullable = false)
	private Integer stock;
	@Column(name = "description", nullable = false, length = 30)
	private String description;
	@ManyToOne
	@JoinColumn(name = "fk_id_category", nullable = false)
	private Category category;
	
	public Product() {
		
	}
	
	

	public Product(Long id, String name, BigDecimal price, boolean active, Integer stock, String description,
			Category category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.active = active;
		this.stock = stock;
		this.description = description;
		this.category = category;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
