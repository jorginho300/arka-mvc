package com.arka.service;

import java.util.List;

import com.arka.dto.ProductPurchaseOrderRequest;
import com.arka.dto.PurchaseOrderResponse;

public interface PurchaseOrderService {
	PurchaseOrderResponse createOrder(Long clienteId, List<ProductPurchaseOrderRequest> products);
	PurchaseOrderResponse findPurchaseOrdersById(Long purchaseOrderId);
	List<PurchaseOrderResponse> findPurchaseOrdersByCustomer(Long customerId);
	
}
