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

    public List<StaffLeave> showStaffLeaveDetails(int staffId){
     return staffLeaveDao.findAllByStaffId(staffId);
    }

    @Transactional
    public void saveLeaveForStaff(StaffLeave staffLeave,int staffId) {
        Staff staff = staffDao.findById(staffId).orElseThrow(EntityNotFoundException::new);
        staff.addLeave(staffLeave);
        staffLeaveDao.save(staffLeave);
    }

    public void calculateTotalSalary(StaffSalary staffSalary) {
        // Get the payment month from the paymentDate
        int paymentMonth = staffSalary.getPaymentDate().getMonthValue();

        // Retrieve staff leave records for the given month
        List<StaffLeave> staffLeaves = staffLeaveDao.findByLeaveMonthAndLeaveYear(paymentMonth, staffSalary.getPaymentDate().getYear());

        // Check if the staff has taken leave during the month
        boolean hasLeave = !staffLeaves.isEmpty();

        // Calculate bonus
        int bonus = calculateBonus(staffSalary, hasLeave);

        // Calculate total incentive
        int totalIncentive = calculateTotalIncentive(staffSalary);

        // Calculate total salary (basic salary + bonus + total incentive - deductions)
        int totalSalary = staffSalary.getBasicSalary() + bonus + totalIncentive - staffSalary.getDeductions();

        // Set the calculated total salary to the staffSalary object
        staffSalary.setTotalSalary(totalSalary);
    }

    private int calculateBonus(StaffSalary staffSalary, boolean hasLeave) {
        return hasLeave ? 0 : staffSalary.getBonus(); // If there's leave, bonus is 0; otherwise, use the existing bonus
    }

    private int calculateTotalIncentive(StaffSalary staffSalary) {
        int incentiveRate = staffSalary.getIncentiveRate();
        int successfulDeliveries = staffSalary.getStaff().getSuccessfulDeliveries();
        return incentiveRate * successfulDeliveries;
    }


}
