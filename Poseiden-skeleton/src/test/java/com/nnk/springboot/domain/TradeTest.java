package com.nnk.springboot.domain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class TradeTest {

    Trade trade;
    Trade trade2;
    @BeforeEach
    public void testSetup() {

        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        trade = new Trade();
        trade.setAccount("Account1");
        trade.setType("Buy");
        trade.setBuyQuantity(100.0);
        trade.setSellQuantity(50.0);
        trade.setBuyPrice(10.5);
        trade.setSellPrice(11.0);
        trade.setBenchmark("Benchmark1");
        trade.setTradeDate(timestamp);
        trade.setSecurity("Security1");
        trade.setStatus("Completed");
        trade.setTrader("Trader1");
        trade.setBook("Book1");
        trade.setCreationName("Creator1");
        trade.setCreationDate(timestamp);
        trade.setRevisionName("Revisor1");
        trade.setRevisionDate(timestamp);
        trade.setDealName("Deal1");
        trade.setDealType("Type1");
        trade.setSourceListId("Source1");
        trade.setSide("Buy");

        trade2 = new Trade();
        trade2.setAccount("Account1");
        trade2.setType("Buy");
        trade2.setBuyQuantity(100.0);
        trade2.setSellQuantity(50.0);
        trade2.setBuyPrice(10.5);
        trade2.setSellPrice(11.0);
        trade2.setBenchmark("Benchmark1");
        trade2.setTradeDate(timestamp);
        trade2.setSecurity("Security1");
        trade2.setStatus("Completed");
        trade2.setTrader("Trader1");
        trade2.setBook("Book1");
        trade2.setCreationName("Creator1");
        trade2.setCreationDate(timestamp);
        trade2.setRevisionName("Revisor1");
        trade2.setRevisionDate(timestamp);
        trade2.setDealName("Deal1");
        trade2.setDealType("Type1");
        trade2.setSourceListId("Source1");
        trade2.setSide("Buy");
    }
    @Test
    public void testTradeCreation() {
        // Arrange
        /*Trade trade = new Trade();
        trade.setAccount("Account1");
        trade.setType("Buy");
        trade.setBuyQuantity(100.0);
        trade.setSellQuantity(50.0);
        trade.setBuyPrice(10.5);
        trade.setSellPrice(11.0);
        trade.setBenchmark("Benchmark1");
        trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
        trade.setSecurity("Security1");
        trade.setStatus("Completed");
        trade.setTrader("Trader1");
        trade.setBook("Book1");
        trade.setCreationName("Creator1");
        trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
        trade.setRevisionName("Revisor1");
        trade.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        trade.setDealName("Deal1");
        trade.setDealType("Type1");
        trade.setSourceListId("Source1");
        trade.setSide("Buy");*/

        // Act & Assert
        assertEquals("Account1", trade.getAccount());
        assertEquals("Buy", trade.getType());
        assertEquals(100.0, trade.getBuyQuantity());
        assertEquals(50.0, trade.getSellQuantity());
        assertEquals(10.5, trade.getBuyPrice());
        assertEquals(11.0, trade.getSellPrice());
        assertEquals("Benchmark1", trade.getBenchmark());
        assertNotNull(trade.getTradeDate());
        assertEquals("Security1", trade.getSecurity());
        assertEquals("Completed", trade.getStatus());
        assertEquals("Trader1", trade.getTrader());
        assertEquals("Book1", trade.getBook());
        assertEquals("Creator1", trade.getCreationName());
        assertNotNull(trade.getCreationDate());
        assertEquals("Revisor1", trade.getRevisionName());
        assertNotNull(trade.getRevisionDate());
        assertEquals("Deal1", trade.getDealName());
        assertEquals("Type1", trade.getDealType());
        assertEquals("Source1", trade.getSourceListId());
        assertEquals("Buy", trade.getSide());
    }

    @Test
    public void testTradeDefaultValues() {
        // Arrange
        Trade trade = new Trade();

        // Act & Assert
        assertNull(trade.getAccount());
        assertNull(trade.getType());
        assertNull(trade.getBuyQuantity());
        assertNull(trade.getSellQuantity());
        assertNull(trade.getBuyPrice());
        assertNull(trade.getSellPrice());
        assertNull(trade.getBenchmark());
        assertNull(trade.getTradeDate());
        assertNull(trade.getSecurity());
        assertNull(trade.getStatus());
        assertNull(trade.getTrader());
        assertNull(trade.getBook());
        assertNull(trade.getCreationName());
        assertNull(trade.getCreationDate());
        assertNull(trade.getRevisionName());
        assertNull(trade.getRevisionDate());
        assertNull(trade.getDealName());
        assertNull(trade.getDealType());
        assertNull(trade.getSourceListId());
        assertNull(trade.getSide());
    }

    @Test
    public void testEquals() {
        /*Trade trade1 = new Trade();
        Trade trade2 = new Trade();*/
        Trade trade3 = new Trade();

        // Setting the same values for trade1 and trade2
        trade.setTradeId(1);
        trade2.setTradeId(1);

        // Different id for trade3
        trade3.setTradeId(2);

        assertEquals(trade, trade2, "Trade objects should be equal");
        assertNotEquals(trade, trade3, "Trade objects should not be equal");
        assertNotEquals(trade, null, "Trade object should not be equal to null");
        assertNotEquals(trade, new Object(), "Trade object should not be equal to a different type");
    }

    @Test
    public void testHashCode() {
        Trade trade1 = new Trade();
        trade1.setTradeId(1);

        Trade trade2 = new Trade();
        trade2.setTradeId(1);

        assertEquals(trade1.hashCode(), trade2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testToString() {
        Trade trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Account1");

        String expectedString = "Trade(tradeId=1, account=Account1, type=null, buyQuantity=null, sellQuantity=null, buyPrice=null, sellPrice=null, benchmark=null, tradeDate=null, security=null, status=null, trader=null, book=null, creationName=null, creationDate=null, revisionName=null, revisionDate=null, dealName=null, dealType=null, sourceListId=null, side=null)"; // Adaptez selon votre méthode toString()

        assertEquals(expectedString, trade.toString(), "toString should return the expected string");
    }

    @Test
    public void testSetAndGetTradeId() {
        Trade trade = new Trade();
        trade.setTradeId(1);

        assertEquals(1, trade.getTradeId(), "getTradeId should return the correct value after setting it");
    }

    @Test
    public void testCanEqual() {
        Trade trade1 = new Trade();
        Trade trade2 = new Trade();

        // Assuming you have implemented canEqual in your Trade class
        assertTrue(trade1.canEqual(trade2), "canEqual should return true for objects of the same type");

        assertFalse(trade1.canEqual(new Object()), "canEqual should return false for different types");
    }
}
