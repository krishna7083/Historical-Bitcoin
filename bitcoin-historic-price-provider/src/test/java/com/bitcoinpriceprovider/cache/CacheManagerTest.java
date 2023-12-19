package com.bitcoinpriceprovider.cache;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

public class CacheManagerTest {

    @Test
    public void testPutAndGet() {
        // Given
        CacheManager<String> cacheManager = new CacheManager<>();
        String key = "testKey";
        String value = "testValue";

        // When
        cacheManager.put(key, value, TimeUnit.MINUTES.toMillis(1));
        String retrievedValue = cacheManager.get(key);

        // Then
        assertEquals(value, retrievedValue);
    }

    @Test
    public void testGetExpiredEntry() throws InterruptedException {
        // Given
        CacheManager<String> cacheManager = new CacheManager<>();
        String key = "testKey";
        String value = "testValue";

        // When
        cacheManager.put(key, value, TimeUnit.SECONDS.toMillis(1));

        // Sleep for a short time to simulate the passage of time
        Thread.sleep(2000);

        String retrievedValue = cacheManager.get(key);

        // Then
        assertNull(retrievedValue);
    }

    @Test
    public void testRemove() {
        // Given
        CacheManager<String> cacheManager = new CacheManager<>();
        String key = "testKey";
        String value = "testValue";

        // When
        cacheManager.put(key, value, TimeUnit.MINUTES.toMillis(1));
        cacheManager.remove(key);
        String retrievedValue = cacheManager.get(key);

        // Then
        assertNull(retrievedValue);
    }

    @Test
    public void testClear() {
        // Given
        CacheManager<String> cacheManager = new CacheManager<>();
        String key1 = "testKey1";
        String value1 = "testValue1";
        String key2 = "testKey2";
        String value2 = "testValue2";

        // When
        cacheManager.put(key1, value1, TimeUnit.MINUTES.toMillis(1));
        cacheManager.put(key2, value2, TimeUnit.MINUTES.toMillis(1));
        cacheManager.clear();
        String retrievedValue1 = cacheManager.get(key1);
        String retrievedValue2 = cacheManager.get(key2);

        // Then
        assertNull(retrievedValue1);
        assertNull(retrievedValue2);
    }
}
