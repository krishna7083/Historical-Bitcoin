package com.bitcoinpriceprovider.model.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyInfoTest {

    @Test
    public void testConstructorAndGetters() {
        CurrencyInfo currencyInfo = new CurrencyInfo("USD", "United States");

        assertEquals("USD", currencyInfo.getCurrency());
        assertEquals("United States", currencyInfo.getCountry());
    }

    @Test
    public void testEqualsAndHashCode() {
        CurrencyInfo currencyInfo1 = new CurrencyInfo("USD", "United States");
        CurrencyInfo currencyInfo2 = new CurrencyInfo("USD", "United States");
        CurrencyInfo currencyInfo3 = new CurrencyInfo("EUR", "Eurozone");

        // Test equals
        assertEquals(currencyInfo1, currencyInfo2);
        assertNotEquals(currencyInfo1, currencyInfo3);

        // Test hashCode
        assertEquals(currencyInfo1.hashCode(), currencyInfo2.hashCode());
        assertNotEquals(currencyInfo1.hashCode(), currencyInfo3.hashCode());
    }

    @Test
    public void testNoArgsConstructor() {
        CurrencyInfo currencyInfo = new CurrencyInfo();

        assertNull(currencyInfo.getCurrency());
        assertNull(currencyInfo.getCountry());
    }

    @Test
    public void testSetterAndGetters() {
        CurrencyInfo currencyInfo = new CurrencyInfo();

        currencyInfo.setCurrency("EUR");
        currencyInfo.setCountry("Eurozone");

        assertEquals("EUR", currencyInfo.getCurrency());
        assertEquals("Eurozone", currencyInfo.getCountry());
    }
}

