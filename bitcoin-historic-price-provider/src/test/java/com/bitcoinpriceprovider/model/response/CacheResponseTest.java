package com.bitcoinpriceprovider.model.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class CacheResponseTest {

    @Test
    public void testConstructorAndGetters() {
        Map<String, Double> bpis = new HashMap<>();
        bpis.put("2023-01-01", 100.0);

        CacheResponse cacheResponse = new CacheResponse(true, "2023-01-01", bpis);

        assertTrue(cacheResponse.isCompleteData());
        assertEquals("2023-01-01", cacheResponse.getStartDate());
        assertEquals(bpis, cacheResponse.getBpis());
    }

    @Test
    public void testEqualsAndHashCode() {
        Map<String, Double> bpis1 = new HashMap<>();
        bpis1.put("2023-01-01", 100.0);

        Map<String, Double> bpis2 = new HashMap<>();
        bpis2.put("2023-01-01", 100.0);

        Map<String, Double> bpis3 = new HashMap<>();
        bpis3.put("2023-01-01", 200.0);

        CacheResponse response1 = new CacheResponse(true, "2023-01-01", bpis1);
        CacheResponse response2 = new CacheResponse(true, "2023-01-01", bpis2);
        CacheResponse response3 = new CacheResponse(false, "2023-01-01", bpis3);

        // Test equals
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);

        // Test hashCode
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    public void testNoArgsConstructor() {
        CacheResponse response = new CacheResponse();

        assertFalse(response.isCompleteData());
        assertNull(response.getStartDate());
        assertNull(response.getBpis());
    }

    @Test
    public void testSetterAndGetters() {
        CacheResponse response = new CacheResponse();

        response.setCompleteData(true);
        response.setStartDate("2023-01-01");

        Map<String, Double> bpis = new HashMap<>();
        bpis.put("2023-01-01", 100.0);

        response.setBpis(bpis);

        assertTrue(response.isCompleteData());
        assertEquals("2023-01-01", response.getStartDate());
        assertEquals(bpis, response.getBpis());
    }
}
