package com.nnk.springboot.controllers;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;


import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(BidListController.class)
public class BidListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;


    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testHome() throws Exception {
        BidList bid1 = new BidList();
        bid1.setAccount("Account1");
        bid1.setType("Type1");
        BidList bid2 = new BidList();
        bid2.setAccount("Account2");
        bid2.setType("Type2");

        when(bidListService.getAll()).thenReturn(Arrays.asList(bid1, bid2));

        mockMvc.perform(get("/bidList/list"))
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidLists"))
                .andExpect(model().attribute("bidLists", Arrays.asList(bid1, bid2)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testAddBidForm() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(view().name("bidList/add"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateSuccess() throws Exception {
        BidList bid = new BidList();
        bid.setAccount("Account");
        bid.setType("Type");

        when(bidListService.saveBidList(any(BidList.class))).thenReturn(bid);

        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .param("account", "Account")
                        .param("type", "Type")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateWithErrors() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .param("account", "") // Empty account to trigger validation error
                        .param("type", "Type")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateForm() throws Exception {
        BidList bid = new BidList();
        bid.setBidListId(1);
        bid.setAccount("Account");
        bid.setType("Type");

        when(bidListService.getBidListById(1)).thenReturn(Optional.of(bid));

        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateFormNotFound() throws Exception {
        when(bidListService.getBidListById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(redirectedUrl("/error"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateBidSuccess() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .with(csrf())
                        .param("account", "Updated Account")
                        .param("type", "Updated Type")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"))
                .andDo(print());
    }


    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testDeleteBid() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(redirectedUrl("/bidList/list"))
                .andDo(print());

        verify(bidListService, times(1)).deleteBidListById(1);
    }
}
