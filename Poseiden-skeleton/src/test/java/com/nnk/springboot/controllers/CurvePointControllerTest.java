package com.nnk.springboot.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(CurveController.class)
public class CurvePointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testHome() throws Exception {
        CurvePoint curve1 = new CurvePoint();
        curve1.setCurveId(1);
        CurvePoint curve2 = new CurvePoint();
        curve2.setCurveId(2);

        when(curvePointService.getAll()).thenReturn(Arrays.asList(curve1, curve2));

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoints"))
                .andExpect(model().attribute("curvePoints", Arrays.asList(curve1, curve2)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testAddBidForm() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateSuccess() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);

        when(curvePointService.saveCurvePoint(any(CurvePoint.class))).thenReturn(curvePoint);

        mockMvc.perform(post("/curvePoint/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("curveId", "1")
                        .param("term", "10.0")
                        .param("value", "15.0")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateWithErrors() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("curveId", "")
                        .param("term", "invalid-term")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateForm() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);

        when(curvePointService.getCurvePointById(1)).thenReturn(Optional.of(curvePoint));

        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateFormNotFound() throws Exception {
        when(curvePointService.getCurvePointById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateCurvePointSuccess() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("curveId", "1")
                        .param("term", "10.0")
                        .param("value", "15.0")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andDo(print());
    }


    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testDeleteCurvePoint() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andDo(print());

        verify(curvePointService, times(1)).deleteCurvePointById(1);
    }
}

