package com.example.deliveryservicemanagement.serviceTest;

import com.example.deliveryservicemanagement.dao.StaffDao;
import com.example.deliveryservicemanagement.ds.Staff;
import com.example.deliveryservicemanagement.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StaffServiceTest {

    @Mock
    private StaffDao staffDao;

    @InjectMocks
    private StaffService staffService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveStaff() {
        Staff staff = new Staff();
        staff.setStaffName("John Doe");

        when(staffDao.save(staff)).thenReturn(staff);

        staffService.saveStaff(staff);

        verify(staffDao, times(1)).save(staff);
    }

    @Test
    public void testDeleteStaffById() {
        int id = 1;

        staffService.deleteStaffById(id);

        verify(staffDao, times(1)).deleteById(id);
    }

    @Test
    public void testFindStaffById() {
        int id = 1;
        Staff staff = new Staff();
        staff.setId(id);
        staff.setStaffName("John Doe");

        when(staffDao.findById(id)).thenReturn(Optional.of(staff));

        Staff foundStaff = staffService.findStaffById(id);

        assertNotNull(foundStaff);
        assertEquals(id, foundStaff.getId());
        assertEquals("John Doe", foundStaff.getStaffName());
    }

    @Test
    public void testFindStaffByIdNotFound() {
        int id = 1;

        when(staffDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> staffService.findStaffById(id));
    }

    @Test
    public void testFindAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff());
        staffList.add(new Staff());

        when(staffDao.findAll()).thenReturn(staffList);

        List<Staff> foundStaffList = staffService.findAllStaff();

        assertEquals(2, foundStaffList.size());
    }

    @Test
    public void testUpdateStaffData() {
        int id = 1;
        Staff staff = new Staff();
        staff.setId(id);
        staff.setStaffName("John Doe");

        when(staffDao.findById(id)).thenReturn(Optional.of(staff));
        when(staffDao.save(staff)).thenReturn(staff);

        staffService.updateStaffData(id, staff);

        verify(staffDao, times(1)).save(staff);
    }

    @Test
    public void testUpdateStaffDataNotFound() {
        int id = 1;
        Staff staff = new Staff();
        staff.setId(id);
        staff.setStaffName("John Doe");

        when(staffDao.findById(id)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> staffService.updateStaffData(id, staff));
    }

    @Test
    public void testFindStaffByName() {
        String name = "John Doe";
        Staff staff = new Staff();
        staff.setStaffName(name);

        when(staffDao.findStaffByStaffName(name)).thenReturn(staff);

        Staff foundStaff = staffService.findStaffByName(name);

        assertNotNull(foundStaff);
        assertEquals(name, foundStaff.getStaffName());
    }
}
