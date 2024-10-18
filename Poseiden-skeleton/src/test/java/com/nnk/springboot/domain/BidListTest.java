package com.nnk.springboot.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import org.junit.jupiter.api.Test;


public class BidListTest {

    @Test
    public void testBidListCreation() {
        // Arrange
        BidList bidList = new BidList();
        bidList.setAccount("Account1");
        bidList.setType("Type1");
        bidList.setBidQuantity(100.0);
        bidList.setAskQuantity(200.0);
        bidList.setBid(10.0);
        bidList.setAsk(20.0);
        bidList.setBenchmark("Benchmark1");
        bidList.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList.setCommentary("Sample commentary");
        bidList.setSecurity("Security1");
        bidList.setStatus("Active");
        bidList.setTrader("Trader1");
        bidList.setBook("Book1");
        bidList.setCreationName("Creator1");
        bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidList.setRevisionName("Revisor1");
        bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList.setDealName("Deal1");
        bidList.setDealType("DealType1");
        bidList.setSourceListId("Source1");
        bidList.setSide("Buy");

        // Act & Assert
        assertEquals("Account1", bidList.getAccount());
        assertEquals("Type1", bidList.getType());
        assertEquals(100.0, bidList.getBidQuantity());
        assertEquals(200.0, bidList.getAskQuantity());
        assertEquals(10.0, bidList.getBid());
        assertEquals(20.0, bidList.getAsk());
        assertEquals("Benchmark1", bidList.getBenchmark());
        assertNotNull(bidList.getBidListDate());
        assertEquals("Sample commentary", bidList.getCommentary());
        assertEquals("Security1", bidList.getSecurity());
        assertEquals("Active", bidList.getStatus());
        assertEquals("Trader1", bidList.getTrader());
        assertEquals("Book1", bidList.getBook());
        assertEquals("Creator1", bidList.getCreationName());
        assertNotNull(bidList.getCreationDate());
        assertEquals("Revisor1", bidList.getRevisionName());
        assertNotNull(bidList.getRevisionDate());
        assertEquals("Deal1", bidList.getDealName());
        assertEquals("DealType1", bidList.getDealType());
        assertEquals("Source1", bidList.getSourceListId());
        assertEquals("Buy", bidList.getSide());
    }


    @Test
    public void testEquals() {
        BidList bidList1 = new BidList();
        BidList bidList2 = new BidList();
        BidList bidList3 = new BidList();

        // Setting the same values for bidList1 and bidList2
        bidList1.setBidListId(1);
        bidList1.setAccount("Account1");
        bidList1.setType("Type1");
        bidList1.setBidQuantity(100.0);
        bidList1.setAskQuantity(200.0);
        bidList1.setBid(10.0);
        bidList1.setAsk(20.0);
        bidList1.setBenchmark("Benchmark1");
        bidList1.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList1.setCommentary("Sample commentary");
        bidList1.setSecurity("Security1");
        bidList1.setStatus("Active");
        bidList1.setTrader("Trader1");
        bidList1.setBook("Book1");
        bidList1.setCreationName("Creator1");
        bidList1.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidList1.setRevisionName("Revisor1");
        bidList1.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList1.setDealName("Deal1");
        bidList1.setDealType("DealType1");
        bidList1.setSourceListId("Source1");
        bidList1.setSide("Buy");

        bidList2.setBidListId(1);
        bidList2.setAccount("Account1");
        bidList2.setType("Type1");
        bidList2.setBidQuantity(100.0);
        bidList2.setAskQuantity(200.0);
        bidList2.setBid(10.0);
        bidList2.setAsk(20.0);
        bidList2.setBenchmark("Benchmark1");
        bidList2.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList2.setCommentary("Sample commentary");
        bidList2.setSecurity("Security1");
        bidList2.setStatus("Active");
        bidList2.setTrader("Trader1");
        bidList2.setBook("Book1");
        bidList2.setCreationName("Creator1");
        bidList2.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidList2.setRevisionName("Revisor1");
        bidList2.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList2.setDealName("Deal1");
        bidList2.setDealType("DealType1");
        bidList2.setSourceListId("Source1");
        bidList2.setSide("Buy");

        // Different id for bidList3
        bidList3.setBidListId(2);

        assertEquals(bidList1, bidList2, "BidList objects should be equal");
        assertNotEquals(bidList1, bidList3, "BidList objects should not be equal");
        assertNotEquals(bidList1, null, "BidList object should not be equal to null");
        assertNotEquals(bidList1, new Object(), "BidList object should not be equal to a different type");
    }

    @Test
    public void testHashCode() {
        BidList bidList1 = new BidList();
        bidList1.setBidListId(1);

        BidList bidList2 = new BidList();
        bidList2.setBidListId(1);

        assertEquals(bidList1.hashCode(), bidList2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    public void testToString() {
        BidList bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Account1");

        String expectedString = "BidList(BidListId=1, account=Account1, type=null, bidQuantity=null, askQuantity=null, bid=null, ask=null, benchmark=null, bidListDate=null, commentary=null, security=null, status=null, trader=null, book=null, creationName=null, creationDate=null, revisionName=null, revisionDate=null, dealName=null, dealType=null, sourceListId=null, side=null)"; // Adaptez selon votre méthode toString()

        assertEquals(expectedString, bidList.toString(), "toString should return the expected string");
    }

    @Test
    public void testSetAndGetBidListId() {
        BidList bidList = new BidList();
        bidList.setBidListId(1);

        assertEquals(1, bidList.getBidListId(), "getBidListId should return the correct value after setting it");
    }

    @Test
    public void testCanEqual() {
        BidList bidList1 = new BidList();
        BidList bidList2 = new BidList();

        // Assuming you have implemented canEqual in your BidList class
        assertTrue(bidList1.canEqual(bidList2), "canEqual should return true for objects of the same type");

        assertFalse(bidList1.canEqual(new Object()), "canEqual should return false for different types");
    }

}
