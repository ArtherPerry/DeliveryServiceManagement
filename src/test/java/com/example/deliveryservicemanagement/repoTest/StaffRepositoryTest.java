package com.example.deliveryservicemanagement.repoTest;

import com.example.deliveryservicemanagement.dao.StaffDao;
import com.example.deliveryservicemanagement.ds.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext
public class StaffRepositoryTest {

    @Autowired
    private StaffDao staffRepository;

    @Autowired
    private TestEntityManager entityManager; // Helper for managing JPA entities in tests

    @BeforeEach
    public void tearDownBefore() {
        staffRepository.deleteAll(); // Clean up database after each test
    }

    @Test
    public void testSaveAndRetrieveStaff() {
        // Given
        Staff staff = new Staff();
        staff.setStaffName("John Doe");
        staff.setTownship("Example Township");
        staff.setAddress("123 Example Street");
        staff.setPhoneNumber("09123456789");

        // When
        Staff savedStaff = staffRepository.save(staff);

        // Then
        assertNotNull(savedStaff.getId()); // Verify that the ID is assigned by the repository

        // Retrieve the staff from the database using TestEntityManager
        Optional<Staff> retrievedStaffOptional = staffRepository.findById(savedStaff.getId());
        assertTrue(retrievedStaffOptional.isPresent()); // Ensure staff is found in the database

        // Verify retrieved staff matches the saved staff
        Staff retrievedStaff = retrievedStaffOptional.get();
        assertEquals(savedStaff, retrievedStaff);
    }

    @Test
    public void testUpdateStaff() {
        // Given
        Staff staff = new Staff();
        staff.setStaffName("John Doe");
        staff.setTownship("Example Township");
        staff.setAddress("123 Example Street");
        staff.setPhoneNumber("09123456789");
        Staff savedStaff = entityManager.persistAndFlush(staff); // Save staff to the database

        // When
        savedStaff.setPhoneNumber("09876543210");
        Staff updatedStaff = staffRepository.save(savedStaff);

        // Then
        assertEquals(savedStaff.getId(), updatedStaff.getId()); // Ensure ID remains unchanged
        assertEquals("09876543210", updatedStaff.getPhoneNumber()); // Verify phone number is updated
    }

    @Test
    public void testDeleteStaff() {
        // Given
        Staff staff = new Staff();
        staff.setStaffName("John Doe");
        staff.setTownship("Example Township");
        staff.setAddress("123 Example Street");
        staff.setPhoneNumber("09123456789");
        Staff savedStaff = entityManager.persistAndFlush(staff); // Save staff to the database

        // When
        staffRepository.delete(savedStaff);

        // Then
        assertFalse(staffRepository.existsById(savedStaff.getId())); // Verify staff is deleted
    }

    @Test
    public void testFindAllStaff() {
        // Given
        Staff staff1 = new Staff();
        staff1.setStaffName("John Doe");
        staff1.setTownship("Example Township");
        staff1.setAddress("123 Example Street");
        staff1.setPhoneNumber("09123456789");
        entityManager.persist(staff1); // Save staff to the database

        Staff staff2 = new Staff();
        staff2.setStaffName("Jane Smith");
        staff2.setTownship("Another Township");
        staff2.setAddress("456 Another Street");
        staff2.setPhoneNumber("09876543210");
        entityManager.persistAndFlush(staff2); // Save staff to the database

        // When
        List<Staff> allStaff = staffRepository.findAll();

        // Then
        assertEquals(2, allStaff.size()); // Verify correct number of staff entities retrieved
    }
}
