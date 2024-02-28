package com.example.deliveryservicemanagement.service;

import com.example.deliveryservicemanagement.dao.StaffDao;
import com.example.deliveryservicemanagement.ds.Staff;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffDao staffDao;

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


}
