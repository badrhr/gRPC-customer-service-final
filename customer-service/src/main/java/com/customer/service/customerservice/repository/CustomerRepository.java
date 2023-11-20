package com.customer.service.customerservice.repository;

import com.customer.service.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}