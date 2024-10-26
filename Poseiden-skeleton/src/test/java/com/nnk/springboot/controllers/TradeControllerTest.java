package com.nnk.springboot.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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

@WebMvcTest(TradeController.class)
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;


    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testHome() throws Exception {
        Trade trade1 = new Trade();
        trade1.setAccount("Account1");
        Trade trade2 = new Trade();
        trade2.setAccount("Account2");

        when(tradeService.getAll()).thenReturn(Arrays.asList(trade1, trade2));

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("trades"))
                .andExpect(model().attribute("trades", Arrays.asList(trade1, trade2)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testAddTradeForm() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateTradeSuccess() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("Account1");

        when(tradeService.saveTrade(any(Trade.class))).thenReturn(trade);

        mockMvc.perform(post("/trade/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("account", "Account1")
                        .param("type", "Type1")
                        .param("buyQuantity", "100")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateTradeWithErrors() throws Exception {
        mockMvc.perform(post("/trade/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("account", "")
                        .param("type", "Invalid Type")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateForm() throws Exception {
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account1");

        when(tradeService.getTradeById(1)).thenReturn(Optional.of(trade));

        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeExists("trade"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateFormNotFound() throws Exception {
        when(tradeService.getTradeById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateTradeSuccess() throws Exception {
        mockMvc.perform(post("/trade/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("account", "UpdatedAccount")
                        .param("type", "UpdatedType")
                        .param("buyQuantity", "150")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateTradeWithErrors() throws Exception {
        mockMvc.perform(post("/trade/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("account", "")
                        .param("type", "Invalid Type")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testDeleteTrade() throws Exception {
        mockMvc.perform(get("/trade/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"))
                .andDo(print());

        verify(tradeService, times(1)).deleteTradeById(1);
    }
}

