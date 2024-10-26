package com.nnk.springboot.configuration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nnk.springboot.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserService userService;

    @Mock
    private com.nnk.springboot.domain.User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Arrange
        String username = "testUser";
        String password = "password";
        String role = "USER";

        // Simulez le comportement de userService
        when(userService.getUserByUserName(username)).thenReturn(Optional.of(user));
        when(user.getUsername()).thenReturn(username);
        when(user.getPassword()).thenReturn(password);
        when(user.getRole()).thenReturn(role);

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonExistingUser";

        // Simulez le comportement de userService
        when(userService.getUserByUserName(username)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });
    }
}

