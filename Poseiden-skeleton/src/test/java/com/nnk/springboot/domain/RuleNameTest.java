package com.nnk.springboot.domain;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class RuleNameTest {

    @Test
    public void testRuleNameCreation() {
        // Arrange
        RuleName ruleName = new RuleName();
        ruleName.setName("Sample Rule");
        ruleName.setDescription("This is a sample rule description.");
        ruleName.setJson("{\"key\":\"value\"}");
        ruleName.setTemplate("Sample Template");
        ruleName.setSqlStr("SELECT * FROM table WHERE condition");
        ruleName.setSqlPart("WHERE condition");

        // Act & Assert
        assertEquals("Sample Rule", ruleName.getName());
        assertEquals("This is a sample rule description.", ruleName.getDescription());
        assertEquals("{\"key\":\"value\"}", ruleName.getJson());
        assertEquals("Sample Template", ruleName.getTemplate());
        assertEquals("SELECT * FROM table WHERE condition", ruleName.getSqlStr());
        assertEquals("WHERE condition", ruleName.getSqlPart());
    }

    @Test
    public void testRuleNameDefaultValues() {
        // Arrange
        RuleName ruleName = new RuleName();

        // Act & Assert
        assertNull(ruleName.getName());
        assertNull(ruleName.getDescription());
        assertNull(ruleName.getJson());
        assertNull(ruleName.getTemplate());
        assertNull(ruleName.getSqlStr());
        assertNull(ruleName.getSqlPart());
    }

    @Test
    public void testEquals() {
        RuleName ruleName1 = new RuleName();

        RuleName ruleName2 = new RuleName();
        RuleName ruleName3 = new RuleName();

        // Setting the same values for ruleName1 and ruleName2
        ruleName1.setId(1);
        ruleName1.setName("Sample Rule");
        ruleName1.setDescription("This is a sample rule description.");
        ruleName1.setJson("{\"key\":\"value\"}");
        ruleName1.setTemplate("Sample Template");
        ruleName1.setSqlStr("SELECT * FROM table WHERE condition");
        ruleName1.setSqlPart("WHERE condition");

        ruleName2.setId(1);
        ruleName2.setName("Sample Rule");
        ruleName2.setDescription("This is a sample rule description.");
        ruleName2.setJson("{\"key\":\"value\"}");
        ruleName2.setTemplate("Sample Template");
        ruleName2.setSqlStr("SELECT * FROM table WHERE condition");
        ruleName2.setSqlPart("WHERE condition");

        // Different id for ruleName3
        ruleName3.setId(2);

        assertEquals(ruleName1, ruleName2, "RuleName objects should be equal");
        assertNotEquals(ruleName1, ruleName3, "RuleName objects should not be equal");
        assertNotEquals(ruleName1, null, "RuleName object should not be equal to null");
        assertNotEquals(ruleName1, new Object(), "RuleName object should not be equal to a different type");
    }

    @Test
    public void testHashCode() {
        RuleName ruleName1 = new RuleName();
        ruleName1.setId(1);

        RuleName ruleName2 = new RuleName();
        ruleName2.setId(1);

        assertEquals(ruleName1.hashCode(), ruleName2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testToString() {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Rule1");
        ruleName.setDescription("Rule Description");
        ruleName.setJson("{\"key\":\"value\"}");
        ruleName.setTemplate("Template");
        ruleName.setSqlStr("SELECT * FROM rule");
        ruleName.setSqlPart("sql part");

        String expectedString = "RuleName(id=1, name=Rule1, description=Rule Description, json={\"key\":\"value\"}, template=Template, sqlStr=SELECT * FROM rule, sqlPart=sql part)"; // Adaptez selon votre méthode toString()

        assertEquals(expectedString, ruleName.toString(), "toString should return the expected string");
    }

    @Test
    public void testSetAndGetId() {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);

        assertEquals(1, ruleName.getId(), "getId should return the correct value after setting it");
    }

    @Test
    public void testCanEqual() {
        RuleName ruleName1 = new RuleName();
        RuleName ruleName2 = new RuleName();

        // Assuming you have implemented canEqual in your RuleName class
        assertTrue(ruleName1.canEqual(ruleName2), "canEqual should return true for objects of the same type");
        assertFalse(ruleName1.canEqual(new Object()), "canEqual should return false for different types");
    }
}
