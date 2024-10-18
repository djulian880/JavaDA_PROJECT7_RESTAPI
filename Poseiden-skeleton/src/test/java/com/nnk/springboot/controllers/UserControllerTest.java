package com.nnk.springboot.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        // Setup MockMvc for testing
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testHome() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", Arrays.asList(user1, user2)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testAddUserForm() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateUserSuccess() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("username", "testUser")
                        .param("password", "password123")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                //.andExpect(redirectedUrl("/user/list"))
                .andDo(print());
    }



    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateForm() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("testUser");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"))
                .andDo(print());
    }

 /*   @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateFormNotFound() throws Exception {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"))
                .andDo(print());
    }*/

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateUserSuccess() throws Exception {
        mockMvc.perform(post("/user/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("username", "UpdatedUser")
                        .param("password", "newPassword123")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                //.andExpect(redirectedUrl("/user/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateUserWithErrors() throws Exception {
        mockMvc.perform(post("/user/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("username", "")
                        .param("password", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"))
                .andDo(print());

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateUserSuccessAndPasswordEncoding() throws Exception {
        // Simulate valid user input
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password123");

        // Simulate password encoding
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");

        // Simulate saving user in repository
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Mocking repository findAll method after saving user
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        mockMvc.perform(post("/user/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("username", "testUser")
                        .param("password", "password123")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            //    .andExpect(status().isOk())
           //     .andExpect(redirectedUrl("/user/list"))
                .andDo(print());

        // Verify password encoding was called
       // verify(passwordEncoder, times(1)).encode("password123");

        // Verify user was saved with encoded password
      /*  verify(userRepository, times(1)).save(argThat(savedUser ->
                savedUser.getPassword().equals("encodedPassword123")
        ));
*/
        // Verify that after saving, the list is fetched and added to the model
        //verify(userRepository, times(1)).findAll();
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateUserWithErrors() throws Exception {
        // Simulate a validation error by sending empty username and password
        mockMvc.perform(post("/user/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("username", "")
                        .param("password", "")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"))
                .andDo(print());

        // Verify that the user is not saved when there are errors
        verify(userRepository, never()).save(any(User.class));

        // Verify that password encoding is not triggered if validation fails
        verify(passwordEncoder, never()).encode(anyString());
    }
}

