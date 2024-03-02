package com.example.deliveryservicemanagement.service;

import com.example.deliveryservicemanagement.dao.StaffDao;
import com.example.deliveryservicemanagement.dao.StaffLeaveDao;
import com.example.deliveryservicemanagement.ds.Staff;
import com.example.deliveryservicemanagement.ds.StaffLeave;
import com.example.deliveryservicemanagement.ds.StaffSalary;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Transactional
    public void saveLeaveForStaff(StaffLeave staffLeave,int staffId) {
        Staff staff = staffDao.findById(staffId).orElseThrow(EntityNotFoundException::new);
        staff.addLeave(staffLeave);
        staffLeaveDao.save(staffLeave);
    }

    public void calculateTotalSalary(StaffSalary staffSalary){
        int totalSalary =(staffSalary.getBasicSalary()+staffSalary.getBonus()+staffSalary.getTotalIncentive())-staffSalary.getDeductions();
        staffSalary.setTotalSalary(totalSalary);
    }

    public void  calculateBonus(StaffSalary staffSalary,List<StaffLeave> leavesThisMonth){
        if(leavesThisMonth.isEmpty()){
           staffSalary.setBonus(1*staffSalary.getBonus());
        }else {
            staffSalary.setBonus(0);
        }
    }

    public int calculateTotalIncentive(Staff staff) {
        List<StaffSalary> staffSalaries = staff.getStaffSalaries();
        int totalIncentive = 0;
       YearMonth currentYearMonth = YearMonth.now();
       for (StaffSalary staffSalary:staffSalaries){
           LocalDate paymentDate = staffSalary.getPaymentDate();
           YearMonth salaryYearMonth = YearMonth.from(paymentDate);
           if(salaryYearMonth.equals(currentYearMonth)){
               totalIncentive+= staffSalary.getTotalIncentive();
           }
       }
        return totalIncentive;
    }
}
