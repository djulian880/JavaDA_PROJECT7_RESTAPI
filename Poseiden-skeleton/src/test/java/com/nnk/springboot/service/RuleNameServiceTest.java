package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameService ruleNameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    public void testGetAll() {
        // Arrange
        RuleName rule1 = new RuleName();
        RuleName rule2 = new RuleName();
        List<RuleName> ruleNameList = Arrays.asList(rule1, rule2);

        when(ruleNameRepository.findAll()).thenReturn(ruleNameList);

        // Act
        List<RuleName> result = ruleNameService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    public void testGetRuleNameById() {
        // Arrange
        RuleName rule = new RuleName();
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));

        // Act
        Optional<RuleName> result = ruleNameService.getRuleNameById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(rule, result.get());
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveRuleName() {
        // Arrange
        RuleName rule = new RuleName();
        when(ruleNameRepository.save(rule)).thenReturn(rule);

        // Act
        RuleName result = ruleNameService.saveRuleName(rule);

        // Assert
        assertEquals(rule, result);
        verify(ruleNameRepository, times(1)).save(rule);
    }

    @Test
    public void testDeleteRuleNameById() {
        // Arrange
        Integer id = 1;

        // Act
        ruleNameService.deleteRuleNameById(id);

        // Assert
        verify(ruleNameRepository, times(1)).deleteById(id);
    }
}
