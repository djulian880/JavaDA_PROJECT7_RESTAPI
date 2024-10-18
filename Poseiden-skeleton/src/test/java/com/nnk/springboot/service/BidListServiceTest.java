package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

public class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    public void testGetAll() {
        // Arrange
        BidList bid1 = new BidList();
        BidList bid2 = new BidList();
        List<BidList> bidList = Arrays.asList(bid1, bid2);

        when(bidListRepository.findAll()).thenReturn(bidList);

        // Act
        List<BidList> result = bidListService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    public void testGetBidListById() {
        // Arrange
        BidList bid = new BidList();
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));

        // Act
        Optional<BidList> result = bidListService.getBidListById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(bid, result.get());
        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveBidList() {
        // Arrange
        BidList bid = new BidList();
        when(bidListRepository.save(bid)).thenReturn(bid);

        // Act
        BidList result = bidListService.saveBidList(bid);

        // Assert
        assertEquals(bid, result);
        verify(bidListRepository, times(1)).save(bid);
    }

    @Test
    public void testDeleteBidListById() {
        // Arrange
        Integer id = 1;

        // Act
        bidListService.deleteBidListById(id);

        // Assert
        verify(bidListRepository, times(1)).deleteById(id);
    }
}