package com.bitcoinpriceprovider.model.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class CoinDeskHistoricAPIResponseTest {

    @Test
    public void testConstructorAndGetters() {
        Map<String, Double> bpi = new HashMap<>();
        bpi.put("2023-01-01", 100.0);

        CoinDeskHistoricAPIResponse response = new CoinDeskHistoricAPIResponse(bpi);

        assertEquals(bpi, response.getBpi());
    }

    @Test
    public void testEqualsAndHashCode() {
        Map<String, Double> bpi1 = new HashMap<>();
        bpi1.put("2023-01-01", 100.0);

        Map<String, Double> bpi2 = new HashMap<>();
        bpi2.put("2023-01-01", 100.0);

        Map<String, Double> bpi3 = new HashMap<>();
        bpi3.put("2023-01-01", 200.0);

        CoinDeskHistoricAPIResponse response1 = new CoinDeskHistoricAPIResponse(bpi1);
        CoinDeskHistoricAPIResponse response2 = new CoinDeskHistoricAPIResponse(bpi2);
        CoinDeskHistoricAPIResponse response3 = new CoinDeskHistoricAPIResponse(bpi3);

        // Test equals
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);

        // Test hashCode
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    public void testNoArgsConstructor() {
        CoinDeskHistoricAPIResponse response = new CoinDeskHistoricAPIResponse();

        assertNull(response.getBpi());
    }

    @Test
    public void testSetterAndGetters() {
        CoinDeskHistoricAPIResponse response = new CoinDeskHistoricAPIResponse();

        Map<String, Double> bpi = new HashMap<>();
        bpi.put("2023-01-01", 100.0);

        response.setBpi(bpi);

        assertEquals(bpi, response.getBpi());
    }
}

