package com.example.deliveryservicemanagement.repoTest;

import com.example.deliveryservicemanagement.ds.Merchant;
import org.apache.bval.jsr.ApacheValidationProvider;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MerchantTest {

    private final ValidatorFactory factory = Validation.byProvider(ApacheValidationProvider.class).configure().buildValidatorFactory();

    private final Validator validator = factory.getValidator();

    @Test
    public void testValidMerchantEntity(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("John Doe");
        merchant.setTownship("Example Township");
        merchant.setAddress("123 Example Street");
        merchant.setPhoneNumber("09123456789");
        Set<ConstraintViolation<Merchant>> violations = validator.validate(merchant);
        assertEquals(0,violations.size(),"Validation should pass for a valid Merchant entity");
    }
    @Test
    public void testInvalidMerchantName(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("");
        merchant.setTownship("Example Township");
        merchant.setAddress("123 Example Street");
        merchant.setPhoneNumber("09123456789");
        Set<ConstraintViolation<Merchant>> violations = validator.validate(merchant);
        assertEquals(1,violations.size(),"Validation should fail for blank merchant name");
    }

    @Test
    public void testInvalidMerchantNameTooLong(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam condimentum velit sed purus blandit");
        Set<ConstraintViolation<Merchant>> violations = validator.validate(merchant);
        boolean hasViolation = violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("merchantName")
                && violation.getMessage().contains("must be at most"));
        assertFalse(hasViolation,"Validation should fail for merchant name exceeding max length");

    }

    @Test
    public void testTownshipBlank(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("John Doe");
        merchant.setTownship("");
        merchant.setAddress("123 Example Street");
        merchant.setPhoneNumber("09123456789");
        Set<ConstraintViolation<Merchant>> violations = validator.validate(merchant);
        assertEquals(1,violations.size(),"Validation should be fail for blank township");

    }

    @Test
    public void  testInvalidPhoneNumberPattern(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("John Doe");
        merchant.setTownship("Example Township");
        merchant.setAddress("123 Example Street");
        merchant.setPhoneNumber("123456789");
        Set<ConstraintViolation<Merchant>> violations = validator.validate(merchant);
        assertEquals(1,violations.size(),"Validation should be fail for invalid phone number pattern");
    }

    @Test
    public void testBlankAddress(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("John Doe");
        merchant.setTownship("Example Township");
        merchant.setAddress("");
        merchant.setPhoneNumber("09123456789");
        Set<ConstraintViolation<Merchant>> violations = validator.validate(merchant);
        assertEquals(1,violations.size(),"Validation should be fail for blank address");
    }

}
