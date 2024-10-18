package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    public void testGetAll() {
        // Arrange
        Trade trade1 = new Trade();
        Trade trade2 = new Trade();
        List<Trade> tradeList = Arrays.asList(trade1, trade2);

        when(tradeRepository.findAll()).thenReturn(tradeList);

        // Act
        List<Trade> result = tradeService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    public void testGetTradeById() {
        // Arrange
        Trade trade = new Trade();
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        // Act
        Optional<Trade> result = tradeService.getTradeById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(trade, result.get());
        verify(tradeRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveTrade() {
        // Arrange
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);

        // Act
        Trade result = tradeService.saveTrade(trade);

        // Assert
        assertEquals(trade, result);
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    public void testDeleteTradeById() {
        // Arrange
        Integer id = 1;

        // Act
        tradeService.deleteTradeById(id);

        // Assert
        verify(tradeRepository, times(1)).deleteById(id);
    }
}
