package com.example.deliveryservicemanagement.service;

import com.example.deliveryservicemanagement.dao.StaffDao;
import com.example.deliveryservicemanagement.dao.StaffLeaveDao;
import com.example.deliveryservicemanagement.ds.Staff;
import com.example.deliveryservicemanagement.ds.StaffLeave;
import com.example.deliveryservicemanagement.ds.StaffSalary;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private StaffLeaveDao staffLeaveDao;

    public void saveStaff(Staff staff) {
        staffDao.save(staff);
    }

    public void deleteStaffById(int id) {
        staffDao.deleteById(id);
    }

    public Staff findStaffById(int id) {
        return staffDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Staff> findAllStaff() {
        return staffDao.findAll();
    }

    public void updateStaffData(int id, Staff staff) {
        if (staffDao.findById(id).isPresent()) {
            staffDao.save(staff);
        }

    }

    public Staff findStaffByName(String name){
        return staffDao.findStaffByStaffName(name);
    }

    public Map<YearMonth, Integer> calculateMonthlyLeaves(Staff staff) {
        List<StaffLeave> staffLeaves = staff.getStaffLeaves();
        Map<YearMonth, Integer> monthlyLeaves = new HashMap<>();

        for (StaffLeave staffLeave : staffLeaves) {
            YearMonth leaveYearMonth = YearMonth.from(staffLeave.getStartDate());
            int currentMonthLeaves = monthlyLeaves.getOrDefault(leaveYearMonth, 0);
            monthlyLeaves.put(leaveYearMonth, currentMonthLeaves + 1);
        }

        return monthlyLeaves;
    }


}
