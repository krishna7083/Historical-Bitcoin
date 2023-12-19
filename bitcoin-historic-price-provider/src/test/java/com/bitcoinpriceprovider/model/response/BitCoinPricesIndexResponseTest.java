package com.bitcoinpriceprovider.model.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class BitCoinPricesIndexResponseTest {

    @Test
    public void testEqualsAndHashCode() {
        BitCoinPricesIndexResponse response1 = new BitCoinPricesIndexResponse("01", "Error", new HashMap<>(), null, null);
        BitCoinPricesIndexResponse response2 = new BitCoinPricesIndexResponse("01", "Error", new HashMap<>(), null, null);

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
    }

    @Test
    public void testNotEquals() {
        BitCoinPricesIndexResponse response1 = new BitCoinPricesIndexResponse("01", "Error", new HashMap<>(), null, null);
        BitCoinPricesIndexResponse response2 = new BitCoinPricesIndexResponse("02", "Different Error", new HashMap<>(), null, null);

        assertNotEquals(response1, response2);
    }


    @Test
    public void testLowAndHighPriceWithEmptyBpi() {
        BitCoinPricesIndexResponse response = new BitCoinPricesIndexResponse("00", "Success", new HashMap<>(), null, null);

        assertNull(response.getLow());
        assertNull(response.getHigh());
    }

    @Test
    public void testNoArgsConstructor() {
        BitCoinPricesIndexResponse response = new BitCoinPricesIndexResponse();

        assertNull(response.getErrocode());
        assertNull(response.getErrormessage());
        assertNull(response.getBpi());
    }

    @Test
    public void testSetterAndGetters() {
        BitCoinPricesIndexResponse response = new BitCoinPricesIndexResponse();

        response.setErrocode("500");
        response.setErrormessage("Internal Server Error");

        Map<String, Double> bpi = new HashMap<>();
        bpi.put("2023-01-01", 100.0);

        response.setBpi(bpi);

        assertEquals("500", response.getErrocode());
        assertEquals("Internal Server Error", response.getErrormessage());
        assertEquals(bpi, response.getBpi());
    }
}