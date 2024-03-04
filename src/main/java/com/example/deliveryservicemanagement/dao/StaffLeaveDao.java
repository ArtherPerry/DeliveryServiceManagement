package com.example.deliveryservicemanagement.dao;

import com.example.deliveryservicemanagement.ds.StaffLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffLeaveDao extends JpaRepository<StaffLeave,Integer> {

    @Query("SELECT sl FROM StaffLeave sl WHERE MONTH(sl.startDate) = :month AND YEAR(sl.startDate) = :year")
    List<StaffLeave> findByLeaveMonthAndLeaveYear(@Param("month") int month, @Param("year") int year);

    List<StaffLeave> findAllByStaffId(@Param("id") int id);
}
