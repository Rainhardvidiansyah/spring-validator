package com.mockito.validator;

import com.mockito.entity.Person;
import com.mockito.entity.Product;
import jakarta.validation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Set;

public class ValidatorFactoryTest {

    @Test
    void testValidatorFactory() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Assertions.assertNotNull(validator);
        validatorFactory.close();
    }

    @Test
    void personValidatorTest() {
        var person = new Person("rainhard", "email@email@email@email@email@email@email@");
        Assertions.assertNotNull(person.getName());
        Assertions.assertNotNull(person.getEmail());
    }

    @Test
    void productNameTest() {
        var product = new Product();
        product.setProductName("Product one named food for all");
        product.getProductName();
    }

    @Test
    void productNameTestWithIllegalStateException() {
        var product = new Product();
        Assertions.assertThrows(IllegalStateException.class,
                ()-> product.setProductName("Product"));
    }

    @Test
    void personDataTestValidator(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Person person = new Person();
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

        Assertions.assertEquals(2, constraintViolations.size());

        for(ConstraintViolation<Person> constraintViolation : constraintViolations){
            System.out.println("Message: " + constraintViolation.getMessage());
            System.out.println("Bean: " + constraintViolation.getLeafBean());
            System.out.println("Annotation: " + constraintViolation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid value: " + constraintViolation.getInvalidValue());
//            System.out.println("For each constrains violation: ");
//            constraintViolation.getPropertyPath().forEach(item -> System.out.println(item.getName()));
        }
        validatorFactory.close();
    }


    @Test
    void personDataSizeTest() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Person person = new Person();
        person.setName("rainhard vidi");
        person.setEmail("rainhard@gmail.com");
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        Assertions.assertEquals(2, constraintViolations.size());
        for(ConstraintViolation<Person> constraintViolation : constraintViolations){
            System.out.println("Message: " + constraintViolation.getMessage());
            System.out.println("Bean: " + constraintViolation.getLeafBean());
            System.out.println("Annotation: " + constraintViolation.getConstraintDescriptor().getAnnotation());
            System.out.println("Invalid value: " + constraintViolation.getInvalidValue());
//            System.out.println("For each constrains violation: ");
//            constraintViolation.getPropertyPath().forEach(item -> System.out.println(item.getName()));
        }
        validatorFactory.close();


    }
}
