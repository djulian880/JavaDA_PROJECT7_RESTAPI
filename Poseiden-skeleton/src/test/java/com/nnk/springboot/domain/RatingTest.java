package com.nnk.springboot.domain;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class RatingTest {

    @Test
    public void testRatingCreation() {
        // Arrange
        Rating rating = new Rating();
        rating.setMoodysRating("Aaa");
        rating.setSandPRating("AAA");
        rating.setFitchRating("AAA");
        rating.setOrderNumber(1);

        // Act & Assert
        assertEquals("Aaa", rating.getMoodysRating());
        assertEquals("AAA", rating.getSandPRating());
        assertEquals("AAA", rating.getFitchRating());
        assertEquals(1, rating.getOrderNumber());
    }

    @Test
    public void testRatingDefaultValues() {
        // Arrange
        Rating rating = new Rating();

        // Act & Assert
        assertNull(rating.getMoodysRating());
        assertNull(rating.getSandPRating());
        assertNull(rating.getFitchRating());
        assertNull(rating.getOrderNumber());
    }

    @Test
    public void testEquals() {
        Rating rating1 = new Rating();
        rating1.setMoodysRating("Aaa");
        rating1.setSandPRating("AAA");
        rating1.setFitchRating("AAA");
        rating1.setOrderNumber(1);

        Rating rating2 = new Rating();
        rating2.setMoodysRating("Aaa");
        rating2.setSandPRating("AAA");
        rating2.setFitchRating("AAA");
        rating2.setOrderNumber(1);

        Rating rating3 = new Rating();

        // Setting the same values for rating1 and rating2
        rating1.setId(1);
        rating2.setId(1);

        // Different id for rating3
        rating3.setId(2);

        assertEquals(rating1, rating2, "Rating objects should be equal");
        assertNotEquals(rating1, rating3, "Rating objects should not be equal");
        assertNotEquals(rating1, null, "Rating object should not be equal to null");
        assertNotEquals(rating1, new Object(), "Rating object should not be equal to a different type");
    }

    @Test
    public void testHashCode() {
        Rating rating1 = new Rating();
        rating1.setId(1);

        Rating rating2 = new Rating();
        rating2.setId(1);

        assertEquals(rating1.hashCode(), rating2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testToString() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("Aa1");
        rating.setSandPRating("AAA");
        rating.setFitchRating("AA");
        rating.setOrderNumber(1);

        String expectedString = "Rating(id=1, moodysRating=Aa1, sandPRating=AAA, fitchRating=AA, orderNumber=1)"; // Adaptez selon votre méthode toString()

        assertEquals(expectedString, rating.toString(), "toString should return the expected string");
    }

    @Test
    public void testSetAndGetId() {
        Rating rating = new Rating();
        rating.setId(1);

        assertEquals(1, rating.getId(), "getId should return the correct value after setting it");
    }

    @Test
    public void testCanEqual() {
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();

        // Assuming you have implemented canEqual in your Rating class
        assertTrue(rating1.canEqual(rating2), "canEqual should return true for objects of the same type");
        assertFalse(rating1.canEqual(new Object()), "canEqual should return false for different types");
    }
}
