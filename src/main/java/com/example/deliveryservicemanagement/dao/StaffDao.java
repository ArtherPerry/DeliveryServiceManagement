package com.example.deliveryservicemanagement.dao;

import com.example.deliveryservicemanagement.ds.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDao extends JpaRepository<Staff,Integer> {

    Staff findStaffByStaffName(String name);
}
