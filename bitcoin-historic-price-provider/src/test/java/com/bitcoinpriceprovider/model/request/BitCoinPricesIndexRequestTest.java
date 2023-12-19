package com.bitcoinpriceprovider.model.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BitCoinPricesIndexRequestTest {

    @Test
    public void testConstructorAndGetters() {
        BitCoinPricesIndexRequest request = new BitCoinPricesIndexRequest("2023-01-01", "2023-02-01", "USD");

        assertEquals("2023-01-01", request.getStartDate());
        assertEquals("2023-02-01", request.getEndDate());
        assertEquals("USD", request.getCurrency());
    }

    @Test
    public void testEqualsAndHashCode() {
        BitCoinPricesIndexRequest request1 = new BitCoinPricesIndexRequest("2023-01-01", "2023-02-01", "USD");
        BitCoinPricesIndexRequest request2 = new BitCoinPricesIndexRequest("2023-01-01", "2023-02-01", "USD");
        BitCoinPricesIndexRequest request3 = new BitCoinPricesIndexRequest("2023-01-01", "2023-02-01", "EUR");

        // Test equals
        assertEquals(request1, request2);
        assertNotEquals(request1, request3);

        // Test hashCode
        assertEquals(request1.hashCode(), request2.hashCode());
        assertNotEquals(request1.hashCode(), request3.hashCode());
    }

    @Test
    public void testNoArgsConstructor() {
        BitCoinPricesIndexRequest request = new BitCoinPricesIndexRequest();

        assertNull(request.getStartDate());
        assertNull(request.getEndDate());
        assertNull(request.getCurrency());
    }

    @Test
    public void testSetterAndGetters() {
        BitCoinPricesIndexRequest request = new BitCoinPricesIndexRequest();

        request.setStartDate("2023-01-01");
        request.setEndDate("2023-02-01");
        request.setCurrency("USD");

        assertEquals("2023-01-01", request.getStartDate());
        assertEquals("2023-02-01", request.getEndDate());
        assertEquals("USD", request.getCurrency());
    }
}