package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;

public class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    public void testGetAll() {
        // Arrange
        CurvePoint curve1 = new CurvePoint();
        CurvePoint curve2 = new CurvePoint();
        List<CurvePoint> curvePointList = Arrays.asList(curve1, curve2);

        when(curvePointRepository.findAll()).thenReturn(curvePointList);

        // Act
        List<CurvePoint> result = curvePointService.getAll();

        // Assert
        assertEquals(2, result.size());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    public void testGetCurvePointById() {
        // Arrange
        CurvePoint curve = new CurvePoint();
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curve));

        // Act
        Optional<CurvePoint> result = curvePointService.getCurvePointById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(curve, result.get());
        verify(curvePointRepository, times(1)).findById(1);
    }

    @Test
    public void testSaveCurvePoint() {
        // Arrange
        CurvePoint curve = new CurvePoint();
        when(curvePointRepository.save(curve)).thenReturn(curve);

        // Act
        CurvePoint result = curvePointService.saveCurvePoint(curve);

        // Assert
        assertEquals(curve, result);
        verify(curvePointRepository, times(1)).save(curve);
    }

    @Test
    public void testDeleteCurvePointById() {
        // Arrange
        Integer id = 1;

        // Act
        curvePointService.deleteCurvePointById(id);

        // Assert
        verify(curvePointRepository, times(1)).deleteById(id);
    }
}
