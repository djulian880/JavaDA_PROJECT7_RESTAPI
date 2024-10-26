package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    public void testGetAll() {
        // Arrange
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        List<Rating> ratingList = Arrays.asList(rating1, rating2);

        when(ratingRepository.findAll()).thenReturn(ratingList);

        // Act
        List<Rating> result = ratingService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    public void testGetRatingById() {
        // Arrange
        Rating rating = new Rating();
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        // Act
        Optional<Rating> result = ratingService.getRatingById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(rating, result.get());
        verify(ratingRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveRating() {
        // Arrange
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);

        // Act
        Rating result = ratingService.saveRating(rating);

        // Assert
        assertEquals(rating, result);
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    public void testDeleteRatingById() {
        // Arrange
        Integer id = 1;

        // Act
        ratingService.deleteRatingById(id);

        // Assert
        verify(ratingRepository, times(1)).deleteById(id);
    }
}
