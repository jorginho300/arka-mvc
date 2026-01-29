package com.arka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arka.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
