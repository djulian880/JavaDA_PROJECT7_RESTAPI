package com.nnk.springboot.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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

@WebMvcTest(RuleNameController.class)
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;


    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testHome() throws Exception {
        RuleName rule1 = new RuleName();
        rule1.setName("Rule 1");
        RuleName rule2 = new RuleName();
        rule2.setName("Rule 2");

        when(ruleNameService.getAll()).thenReturn(Arrays.asList(rule1, rule2));

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleNames"))
                .andExpect(model().attribute("ruleNames", Arrays.asList(rule1, rule2)))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testAddRuleForm() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateSuccess() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setName("Rule 1");

        when(ruleNameService.saveRuleName(any(RuleName.class))).thenReturn(ruleName);

        mockMvc.perform(post("/ruleName/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("name", "Rule 1")
                        .param("description", "Description")
                        .param("json", "{\"key\":\"value\"}")
                        .param("template", "Template")
                        .param("sqlStr", "SELECT *")
                        .param("sqlPart", "WHERE 1=1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testValidateWithErrors() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("name", "")
                        .param("description", "Invalid description")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateForm() throws Exception {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Rule 1");

        when(ruleNameService.getRuleNameById(1)).thenReturn(Optional.of(ruleName));

        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testShowUpdateFormNotFound() throws Exception {
        when(ruleNameService.getRuleNameById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateRuleNameSuccess() throws Exception {
        mockMvc.perform(post("/ruleName/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("name", "Rule 1")
                        .param("description", "Updated Description")
                        .param("json", "{\"key\":\"value\"}")
                        .param("template", "Updated Template")
                        .param("sqlStr", "SELECT * FROM Updated")
                        .param("sqlPart", "WHERE 1=1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testUpdateRuleNameWithErrors() throws Exception {
        mockMvc.perform(post("/ruleName/update/1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Added CSRF token
                        .param("name", "")
                        .param("description", "Invalid description")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testDeleteRuleName() throws Exception {
        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andDo(print());

        verify(ruleNameService, times(1)).deleteRuleNameById(1);
    }
}

