package com.mockito.validator;

import com.mockito.entity.Person;
import com.mockito.entity.Ticket;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class NestedValidationTest {

    private ValidatorFactory validatorFactory;
    private Validator validator;

    @BeforeEach
    void setUP(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    @Test
    void testNested() {
        var person = new Person();
        Ticket ticket = new Ticket();
        person.setName("rainhard");
        person.setEmail("rainhard@gmail.com");
        person.setTicket(ticket);

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        for(ConstraintViolation<Person> constraintViolation : constraintViolations){
            System.out.println(constraintViolation.getMessage());
            System.out.println(constraintViolation.getPropertyPath());
            System.out.println("++++++");
        }

    }
}
