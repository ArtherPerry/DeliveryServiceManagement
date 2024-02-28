package com.example.deliveryservicemanagement.controllerTest;

import com.example.deliveryservicemanagement.controller.StaffController;
import com.example.deliveryservicemanagement.ds.Staff;
import com.example.deliveryservicemanagement.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StaffControllerTest {

    @Mock
    private StaffService staffService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    private StaffController staffController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        staffController = new StaffController(staffService);
    }

    @Test
    void saveStaff_ValidStaff_RedirectToFindAll() {
        Staff staff = new Staff();
        String viewName = staffController.saveStaff(staff, bindingResult);
        assertEquals("redirect:/staff/findAll", viewName);
        verify(staffService, times(1)).saveStaff(staff);
    }

    @Test
    void findStaffById_ValidId_ModelContainsStaff() {
        int id = 1;
        Staff staff = new Staff();
        when(staffService.findStaffById(id)).thenReturn(staff);
        String viewName = staffController.findStaffById(id, model);
        assertEquals("staffDetail", viewName);
        verify(model, times(1)).addAttribute("staff", staff);
    }

    @Test
    void findAllStaff_ModelContainsStaffList() {
        List<Staff> staffList = new ArrayList<>();
        when(staffService.findAllStaff()).thenReturn(staffList);
        String viewName = staffController.findAllStaff(model);
        assertEquals("allStaff", viewName);
        verify(model, times(1)).addAttribute("staffs", staffList);
    }

    @Test
    void deleteStaff_ValidId_RedirectToFindAll() {
        int id = 1;
        String viewName = staffController.deleteStaff(id);
        assertEquals("redirect:/staff/findAll", viewName);
        verify(staffService, times(1)).deleteStaffById(id);
    }

    @Test
    void updateStaffData_ValidId_ValidStaff_RedirectToFindAll() {
        int id = 1;
        Staff staff = new Staff();
        String viewName = staffController.updateStaffData(id, staff, bindingResult);
        assertEquals("redirect:/staff/findAll", viewName);
        verify(staffService, times(1)).updateStaffData(id, staff);
    }

    @Test
    void findStaffByName_ValidName_ModelContainsStaff() {
        String name = "John Doe";
        Staff staff = new Staff();
        when(staffService.findStaffByName(name)).thenReturn(staff);
        String viewName = staffController.findStaffByName(name, model);
        assertEquals("staffDetail", viewName);
        verify(model, times(1)).addAttribute("staffName", staff);
    }
}
