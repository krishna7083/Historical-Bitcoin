package com.bitcoinpriceprovider.cache;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

public class CacheEntryTest {

    @Test
    public void testConstructorAndGetters() {
        // Given
        String value = "TestValue";
        long expirationTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10);

        // When
        CacheEntry<String> cacheEntry = new CacheEntry<>(value, expirationTime);

        // Then
        assertEquals(value, cacheEntry.getValue());
        assertEquals(false, cacheEntry.isExpired());
    }

    @Test
    public void testIsExpired() throws InterruptedException {
        // Given
        String value = "TestValue";
        long expirationTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(1);

        // When
        CacheEntry<String> cacheEntry = new CacheEntry<>(value, expirationTime);

        // Sleep for a short time to simulate the passage of time
        Thread.sleep(2000);

        // Then
        assertTrue(cacheEntry.isExpired());
    }

    @Test
    public void testIsNotExpired() {
        // Given
        String value = "TestValue";
        long expirationTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10);

        // When
        CacheEntry<String> cacheEntry = new CacheEntry<>(value, expirationTime);

        // Then
        assertFalse(cacheEntry.isExpired());
    }

    @Test
    public void testIsExpiredForNegativeExpirationTime() {
        // Given
        String value = "TestValue";
        long expirationTime = -1; // Negative expiration time

        // When
        CacheEntry<String> cacheEntry = new CacheEntry<>(value, expirationTime);

        // Then
        assertTrue(cacheEntry.isExpired());
    }

    @Test
    public void testIsExpiredForZeroExpirationTime() {
        // Given
        String value = "TestValue";
        long expirationTime = 0; // Zero expiration time

        // When
        CacheEntry<String> cacheEntry = new CacheEntry<>(value, expirationTime);

        // Then
        assertTrue(cacheEntry.isExpired());
    }
}

