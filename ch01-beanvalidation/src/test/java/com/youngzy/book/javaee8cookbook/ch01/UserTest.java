package com.youngzy.book.javaee8cookbook.ch01;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class UserTest {
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void validUser() {
        User user = new User(
                "elder",
                "elder@eldermoraes.com",
                asList(1,2));

        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertTrue(cv.isEmpty());
    }

    @Test
    public void invalidName() {
        User user = new User("");

        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidEmail() {
        User user = new User(
                "elder",
                "elder-eldermoraes_com",
                asList(1,2));

        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(1, cv.size());
    }

    @Test
    public void invalidId() {
        User user = new User(
                "elder",
                "elder@eldermoraes.com",
                asList(-1,-2,1,2));

        Set<ConstraintViolation<User>> cv = validator.validate(user);
        assertEquals(2, cv.size());
    }
}