package com.example.deliveryservicemanagement.dao;

import com.example.deliveryservicemanagement.ds.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Long> {
}
