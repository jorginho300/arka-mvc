package com.arka.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.arka.enums.PurchaseOrderEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "fk_id_customer", nullable = false)
	private Customer customer;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PurchaseOrderEnum status;
	@Column(name = "created_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;
	@Column(name = "completed_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime completedAt;
	@Column(name = "total", nullable = false)
	private BigDecimal total;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseOrderDetail> details = new ArrayList<>();
	
	public void addDetail(PurchaseOrderDetail detail) {
		details.add(detail);
		detail.setPurchaseOrder(this);
	}
	
	public PurchaseOrder() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PurchaseOrderDetail> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseOrderDetail> details) {
		this.details = details;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PurchaseOrderEnum getStatus() {
		return status;
	}

	public void setStatus(PurchaseOrderEnum status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public PurchaseOrder(Long id, Customer customer, PurchaseOrderEnum status, LocalDateTime createdAt,
			LocalDateTime completedAt, BigDecimal total, List<PurchaseOrderDetail> details) {
		super();
		this.id = id;
		this.customer = customer;
		this.status = status;
		this.createdAt = createdAt;
		this.completedAt = completedAt;
		this.total = total;
		this.details = details;
	}
	
	@PrePersist
	@PreUpdate
	private void totalCalculate() {
		this.total = details.stream()
				.map(PurchaseOrderDetail::getSubtotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
