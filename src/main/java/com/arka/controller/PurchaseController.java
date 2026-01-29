package com.arka.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.dto.ProductPurchaseOrderRequest;
import com.arka.dto.PurchaseOrderResponse;
import com.arka.service.PurchaseOrderService;

@RestController
@RequestMapping("/apiarka/orders")
public class PurchaseController {
	
	private final PurchaseOrderService orderService;
	
	public PurchaseController(PurchaseOrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping("/customer/{customerId}/create")
	public ResponseEntity<PurchaseOrderResponse> createOrder
	(@PathVariable Long customerId, @RequestBody List<ProductPurchaseOrderRequest>  products) {
		if(products == null || products.isEmpty()) {
			return ResponseEntity.badRequest().build();
		} else {
			PurchaseOrderResponse orderCreated = orderService.createOrder(customerId, products);
			return ResponseEntity.status(HttpStatus.CREATED).body(orderCreated);
		}
	}
	
	@GetMapping("/customer/{customerId}/history")
	public ResponseEntity<List<PurchaseOrderResponse>> findPurchasesByCustomer(@PathVariable Long clienteId) {
		List<PurchaseOrderResponse> purchases = orderService.findPurchaseOrdersByCustomer(clienteId);
		return ResponseEntity.ok(purchases);
	}
}
