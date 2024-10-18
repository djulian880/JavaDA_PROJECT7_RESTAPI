package com.nnk.springboot.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collection;
import java.util.Collections;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserDetails userDetails;

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "ADMIN")
    public void testAdminHomeRedirect() throws Exception {
        mockMvc.perform(get("/admin/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"))
                .andDo(print());
    }
/*
    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void test403ErrorPage() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andDo(print());
    }*/

    /*
    @Test
    @WithMockUser(username = "admin@example.com", roles = "ADMIN")
    public void testLoginSuccessfulAdminRedirect() throws Exception {
        // Simuler un UserDetails avec un rôle d'admin
        UserDetails userDetails = mock(UserDetails.class);
        GrantedAuthority authority = mock(GrantedAuthority.class);
        when(authority.getAuthority()).thenReturn("ROLE_ADMIN");
      //  when(userDetails.getAuthorities()).thenReturn(Collections.singleton(authority));

        mockMvc.perform(get("/loginsuccessful")
                        .with(SecurityMockMvcRequestPostProcessors.user(userDetails)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/home"));
    }

    @Test
    @WithMockUser(username = "user@example.com", roles = "USER")
    public void testLoginSuccessfulUserRedirect() throws Exception {
        // Simuler un UserDetails avec un rôle d'utilisateur
        UserDetails userDetails = mock(UserDetails.class);
        GrantedAuthority authority = mock(GrantedAuthority.class);
        when(authority.getAuthority()).thenReturn("ROLE_USER");
       // when(userDetails.getAuthorities()).thenReturn(Collections.singleton(authority));

        mockMvc.perform(get("/loginsuccessful")
                        .with(SecurityMockMvcRequestPostProcessors.user(userDetails)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }*/
}

