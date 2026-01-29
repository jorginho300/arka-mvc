package com.arka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
