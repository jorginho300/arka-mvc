package com.arka.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderResponse {
	private Long purchaseOrderId;
	private Long customerId;
	private String customerName;
	private LocalDateTime purchaseOrderDate;
	private Integer productQuantity;
	private List<PurchaseOrderDetailResponse> details;
	private BigDecimal total;
	
	public PurchaseOrderResponse() {
		
	}

	public PurchaseOrderResponse(Long purchaseOrderId, Long customerId, String customerName,
			LocalDateTime purchaseOrderDate, Integer productQuantity, List<PurchaseOrderDetailResponse> details,
			BigDecimal total) {
		this.purchaseOrderId = purchaseOrderId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.purchaseOrderDate = purchaseOrderDate;
		this.productQuantity = productQuantity;
		this.details = details;
		this.total = total;
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDateTime getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(LocalDateTime purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public List<PurchaseOrderDetailResponse> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseOrderDetailResponse> details) {
		this.details = details;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
}
