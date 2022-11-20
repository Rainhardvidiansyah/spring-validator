package com.mockito.validator;

import com.mockito.entity.Payment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class PaymentHibernateValidatorTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    @BeforeEach
    void setUP() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    @Test
    void paymentTest() {
        var payment = new Payment();
        payment.setOrderId("first order");
        payment.setAmount(10000L);
        payment.setCreditCard("5555555555554444");

        Set<ConstraintViolation<Payment>> constraintViolations = validator.validate(payment);
        for (ConstraintViolation<Payment> constraintViolation: constraintViolations) {
            System.out.println(constraintViolation.getMessage());
            System.out.println(constraintViolation.getInvalidValue());
            System.out.println("+++++++++++");
        }
    }
}
