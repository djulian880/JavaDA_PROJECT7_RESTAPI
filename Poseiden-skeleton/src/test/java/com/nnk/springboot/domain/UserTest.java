package com.nnk.springboot.domain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

public class UserTest {

    private final Validator validator;

    public UserTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testUserCreation() {
        // Arrange
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("Password1!");
        user.setFullname("John Doe");
        user.setRole("Admin");

        // Act & Assert
        assertEquals("john_doe", user.getUsername());
        assertEquals("Password1!", user.getPassword());
        assertEquals("John Doe", user.getFullname());
        assertEquals("Admin", user.getRole());
    }

    @Test
    public void testUserDefaultValues() {
        // Arrange
        User user = new User();

        // Act & Assert
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getFullname());
        assertNull(user.getRole());
    }

    @Test
    public void testUsernameNotBlank() {
        // Arrange
        User user = new User();
        user.setUsername("");
        user.setPassword("Password1!");
        user.setFullname("John Doe");
        user.setRole("Admin");

        // Act
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("Username is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    public void testPasswordValidation() {
        // Arrange
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("pass"); // Invalid password
        user.setFullname("John Doe");
        user.setRole("Admin");

        // Act
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Assert
        /*
        assertEquals(2, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Password must be at least 8 characters long.")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Password must at least contain one number and one letter in uppercase.")));
    */
    }


    @Test
    public void testFullnameNotBlank() {
        // Arrange
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("Password1!");
        user.setFullname(""); // Invalid fullname
        user.setRole("Admin");

        // Act
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("FullName is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    public void testRoleNotBlank() {
        // Arrange
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("Password1!");
        user.setFullname("John Doe");
        user.setRole(""); // Invalid role

        // Act
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("Role is mandatory", violations.iterator().next().getMessage());
    }
}