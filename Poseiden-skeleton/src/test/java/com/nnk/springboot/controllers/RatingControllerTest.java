package com.nnk.springboot.controllers;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @BeforeEach
    public void setup() {
        // Setup MockMvc for testing
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testHome() throws Exception {
        Rating rating1 = new Rating();
        rating1.setOrderNumber(1);
        Rating rating2 = new Rating();
        rating2.setOrderNumber(2);

        when(ratingService.getAll()).thenReturn(Arrays.asList(rating1, rating2));

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("ratings"))
                .andExpect(model().attribute("ratings", Arrays.asList(rating1, rating2)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testAddRatingForm() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateSuccess() throws Exception {
        Rating rating = new Rating();
        rating.setOrderNumber(1);

        when(ratingService.saveRating(any(Rating.class))).thenReturn(rating);

        mockMvc.perform(post("/rating/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("orderNumber", "1")
                        .param("moodysRating", "A1")
                        .param("sandPRating", "A+")
                        .param("fitchRating", "A")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"))
                .andDo(print());
    }
/*
    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateWithErrors() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("orderNumber", "")
                        .param("moodysRating", "invalid-rating")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"))
                .andDo(print());
    }*/

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateForm() throws Exception {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setOrderNumber(1);

        when(ratingService.getRatingById(1)).thenReturn(Optional.of(rating));

        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateFormNotFound() throws Exception {
        when(ratingService.getRatingById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateRatingSuccess() throws Exception {
        mockMvc.perform(post("/rating/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("orderNumber", "1")
                        .param("moodysRating", "A1")
                        .param("sandPRating", "A+")
                        .param("fitchRating", "A")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"))
                .andDo(print());
    }

  /*  @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateRatingWithErrors() throws Exception {
        mockMvc.perform(post("/rating/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("orderNumber", "")
                        .param("moodysRating", "invalid-rating")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("/rating/update{1}"))
                .andDo(print());
    }*/

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testDeleteRating() throws Exception {
        mockMvc.perform(get("/rating/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"))
                .andDo(print());

        verify(ratingService, times(1)).deleteRatingById(1);
    }
}

