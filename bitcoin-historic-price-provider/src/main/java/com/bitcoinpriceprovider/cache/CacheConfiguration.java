package com.bitcoinpriceprovider.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Map;

@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager<Map<LocalDate, Double>> cacheManager() {
        return new CacheManager<>();
    }
}
