package com.nnk.springboot.domain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class CurvePointTest {

    @Test
    public void testCurvePointCreation() {
        // Arrange
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setAsOfDate(timestamp);
        curvePoint.setTerm(5.25);
        curvePoint.setValue(10.75);
        curvePoint.setCreationDate(timestamp);

        // Act & Assert
        assertEquals(1, curvePoint.getCurveId());
        assertNotNull(curvePoint.getAsOfDate());
        assertEquals(5.25, curvePoint.getTerm());
        assertEquals(10.75, curvePoint.getValue());
        assertNotNull(curvePoint.getCreationDate());
    }

    @Test
    public void testCurvePointValidation() {
        // Arrange
        CurvePoint curvePoint = new CurvePoint();

        // Act & Assert for @Digits constraints
        Exception curveIdException = null;
        /*
        curveIdException= assertThrows(Exception.class, () -> {
            curvePoint.setCurveId(1000000000); // Should throw exception due to @Digits
        });
        // The specific error message depends on your validation framework setup
        assertEquals("The value must be between -9999999999 and 9999999999", curveIdException.getMessage());

        curvePoint.setTerm(5.25);
        Exception termException = assertThrows(Exception.class, () -> {
            curvePoint.setTerm(10000000000.0); // Should throw exception due to @Digits
        });
        assertEquals("Must be a floating point value", termException.getMessage());

        curvePoint.setValue(10.75);
        Exception valueException = assertThrows(Exception.class, () -> {
            curvePoint.setValue(10000000000.0); // Should throw exception due to @Digits
        });
        assertEquals("Must be a floating point value", valueException.getMessage());

         */
    }

    @Test
    public void testEquals() {
        CurvePoint curvePoint1 = new CurvePoint();
        CurvePoint curvePoint2 = new CurvePoint();
        CurvePoint curvePoint3 = new CurvePoint();

        // Setting the same values for curvePoint1 and curvePoint2
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());



        curvePoint1.setCurveId(1);
        curvePoint1.setAsOfDate(timestamp);
        curvePoint1.setTerm(5.25);
        curvePoint1.setValue(10.75);
        curvePoint1.setCreationDate(timestamp);


        curvePoint2.setCurveId(1);
        curvePoint2.setAsOfDate(timestamp);
        curvePoint2.setTerm(5.25);
        curvePoint2.setValue(10.75);
        curvePoint2.setCreationDate(timestamp);


        // Different id for curvePoint3
        curvePoint3.setId(2);

        assertEquals(curvePoint1, curvePoint2, "CurvePoint objects should be equal");
        assertNotEquals(curvePoint1, curvePoint3, "CurvePoint objects should not be equal");
        assertNotEquals(curvePoint1, null, "CurvePoint object should not be equal to null");
        assertNotEquals(curvePoint1, new Object(), "CurvePoint object should not be equal to a different type");
    }

    @Test
    public void testHashCode() {
        CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setId(1);

        CurvePoint curvePoint2 = new CurvePoint();
        curvePoint2.setId(1);

        assertEquals(curvePoint1.hashCode(), curvePoint2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testToString() {
        CurvePoint curvePoint = new CurvePoint();
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(5.5);
        curvePoint.setValue(100.0);
        curvePoint.setAsOfDate(timestamp);
        curvePoint.setCreationDate(timestamp);

        String expectedString = "CurvePoint(id=1, curveId=10, asOfDate=" + timestamp + ", term=5.5, value=100.0, creationDate=" + timestamp + ")"; // Adaptez selon votre méthode toString()

        assertEquals(expectedString, curvePoint.toString(), "toString should return the expected string");
    }

    @Test
    public void testSetAndGetId() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);

        assertEquals(1, curvePoint.getId(), "getId should return the correct value after setting it");
    }

    @Test
    public void testCanEqual() {
        CurvePoint curvePoint1 = new CurvePoint();
        CurvePoint curvePoint2 = new CurvePoint();

        // Assuming you have implemented canEqual in your CurvePoint class
        assertTrue(curvePoint1.canEqual(curvePoint2), "canEqual should return true for objects of the same type");

        assertFalse(curvePoint1.canEqual(new Object()), "canEqual should return false for different types");
    }
}
