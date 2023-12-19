package com.bitcoinpriceprovider.cache;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest(classes = CacheConfiguration.class)
public class CacheConfigurationTest {

    @Autowired
    private CacheManager<Map<LocalDate, Double>> cacheManager;

    @Test
    public void testCacheManagerBean() {
        assertNotNull(cacheManager);
    }
}

