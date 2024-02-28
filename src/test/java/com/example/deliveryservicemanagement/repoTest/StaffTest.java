package com.example.deliveryservicemanagement.repoTest;

import com.example.deliveryservicemanagement.ds.Staff;
import org.apache.bval.jsr.ApacheValidationProvider;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class StaffTest {

    private final ValidatorFactory factory = Validation.byProvider(ApacheValidationProvider.class)
            .configure()
            .buildValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    public void testValidStaffEntity() {
        Staff staff = new Staff();
        staff.setStaffName("John Doe");
        staff.setTownship("Example Township");
        staff.setAddress("123 Example Street");
        staff.setPhoneNumber("09123456789");

        Set<ConstraintViolation<Staff>> violations = validator.validate(staff);
        assertEquals(0, violations.size(), "Validation should pass for a valid Staff entity");
    }

    @Test
    public void testInvalidStaffNameBlank() {
        Staff staff = new Staff();
        staff.setStaffName("");
        staff.setTownship("Example Township");
        staff.setAddress("123 Example Street");
        staff.setPhoneNumber("09123456789");

        Set<ConstraintViolation<Staff>> violations = validator.validate(staff);
        assertEquals(1, violations.size(), "Validation should fail for blank staff name");
    }

    @Test
    public void testInvalidStaffNameTooLong() {
        // Test case for a staff name exceeding the maximum allowed length
        Staff staff = new Staff();
        staff.setStaffName("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam condimentum velit sed purus blandit");

        // Validate the staff entity
        Set<ConstraintViolation<Staff>> violations = validator.validate(staff);

        // Check if there are violations related to the staff name exceeding the max length
        boolean hasViolation = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("staffName")
                        && violation.getMessage().contains("must be at most"));
        assertFalse(hasViolation, "Validation should fail for staff name exceeding max length");

    }

    @Test
    public void testInvalidTownshipBlank() {
        Staff staff = new Staff();
        staff.setStaffName("John Doe");
        staff.setTownship("");
        staff.setAddress("123 Example Street");
        staff.setPhoneNumber("09123456789");

        Set<ConstraintViolation<Staff>> violations = validator.validate(staff);
        assertEquals(1, violations.size(), "Validation should fail for blank township");
    }

    @Test
    public void testInvalidAddressBlank() {
        Staff staff = new Staff();
        staff.setStaffName("John Doe");
        staff.setTownship("Example Township");
        staff.setAddress("");
        staff.setPhoneNumber("09123456789");

        Set<ConstraintViolation<Staff>> violations = validator.validate(staff);
        assertEquals(1, violations.size(), "Validation should fail for blank address");
    }

    @Test
    public void testInvalidPhoneNumberPattern() {
        Staff staff = new Staff();
        staff.setStaffName("John Doe");
        staff.setTownship("Example Township");
        staff.setAddress("123 Example Street");
        staff.setPhoneNumber("123456789"); // Invalid pattern

        Set<ConstraintViolation<Staff>> violations = validator.validate(staff);
        assertEquals(1, violations.size(), "Validation should fail for invalid phone number pattern");
    }
}
