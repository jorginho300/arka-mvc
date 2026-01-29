package com.arka.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.arka.dto.ProductPurchaseOrderRequest;
import com.arka.dto.PurchaseOrderDetailResponse;
import com.arka.dto.PurchaseOrderResponse;
import com.arka.enums.PurchaseOrderEnum;
import com.arka.exceptions.NoStockEnoughException;
import com.arka.model.Customer;
import com.arka.model.Product;
import com.arka.model.PurchaseOrder;
import com.arka.model.PurchaseOrderDetail;
import com.arka.repository.CustomerRepository;
import com.arka.repository.ProductRepository;
import com.arka.repository.PurchaseOrderRepository;

import jakarta.transaction.Transactional;

@Service
@org.springframework.transaction.annotation.Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final PurchaseOrderRepository orderRepository;
	
	public PurchaseOrderServiceImpl(CustomerRepository customerRepository, 
			PurchaseOrderRepository orderRepository,
			ProductRepository productRepository) {
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@Override
	public PurchaseOrderResponse createOrder(Long clienteId, List<ProductPurchaseOrderRequest> products) {
		Customer customer = customerRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Customer doesn't exists"));
		
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setCustomer(customer);
		purchaseOrder.setCreatedAt(LocalDateTime.now());
		BigDecimal totalPurchaseOrder = BigDecimal.ZERO;
		
		for(ProductPurchaseOrderRequest request : products) {
			Product product = productRepository.findByIdWithLock(request.getProductId())
					.orElseThrow(() -> new RuntimeException("Product Id doesn't exists"));
			
			if(product.getStock() < request.getQuantity()) {
				throw new NoStockEnoughException(product.getId(), product.getStock(), request.getQuantity());
			} else {
				PurchaseOrderDetail detail = new PurchaseOrderDetail();
				detail.setProduct(product);
				detail.setQuantity(request.getQuantity());
				detail.setUnitPrice(product.getPrice());
				//BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
				detail.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
				purchaseOrder.addDetail(detail);
				purchaseOrder.setStatus(PurchaseOrderEnum.PENDING);
				product.setStock(product.getStock() - request.getQuantity());
				productRepository.save(product);
				//totalPurchaseOrder.add(subtotal);
	
			}
		}
		//purchaseOrder.setTotal(totalPurchaseOrder);
		PurchaseOrder savedPurchaseOrder = orderRepository.save(purchaseOrder);
		return toPurchaseOrderResponse(savedPurchaseOrder);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public PurchaseOrderResponse findPurchaseOrdersById(Long purchaseOrderId) {
		PurchaseOrder order = orderRepository.findById(purchaseOrderId)
				.orElseThrow(() -> new RuntimeException("Id Purchase Order Not Found"));
		return toPurchaseOrderResponse(order);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<PurchaseOrderResponse> findPurchaseOrdersByCustomer(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer doesn't exists"));
		return customer.getPurchaseOrders().stream()
				.map(this::toPurchaseOrderResponse)
				.collect(Collectors.toList());
	}
	
	private PurchaseOrderResponse toPurchaseOrderResponse(PurchaseOrder order) {
		PurchaseOrderResponse response = new PurchaseOrderResponse();
		response.setPurchaseOrderId(order.getId());
		response.setCustomerId(order.getCustomer().getId());
		response.setCustomerName(order.getCustomer().getName());
		response.setPurchaseOrderDate(order.getCreatedAt());
		response.setTotal(order.getTotal());
		
		Integer totalQuantity = order.getDetails().stream()
				.mapToInt(PurchaseOrderDetail::getQuantity)
				.sum();
		response.setProductQuantity(totalQuantity);
		
		List<PurchaseOrderDetailResponse> detailsResponse = order.getDetails().stream()
				.map(detail -> {
					PurchaseOrderDetailResponse detailResponse = new PurchaseOrderDetailResponse();
					detailResponse.setProductId(detail.getProduct().getId());
					detailResponse.setProductName(detail.getProduct().getName());
					detailResponse.setQuantity(detail.getQuantity());
					detailResponse.setUnitPrice(detail.getUnitPrice());
					detailResponse.setSubtotal(detail.getSubtotal());
					return detailResponse;
				})
				.collect(Collectors.toList());
		response.setDetails(detailsResponse);
		return response;
	}
}
