package com.example.deliveryservicemanagement.dao;

import com.example.deliveryservicemanagement.ds.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderDao extends JpaRepository<CustomerOrder,Long> {
}
