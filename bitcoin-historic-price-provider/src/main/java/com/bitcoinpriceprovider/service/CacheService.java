package com.bitcoinpriceprovider.service;

import com.bitcoinpriceprovider.cache.CacheManager;
import com.bitcoinpriceprovider.model.response.CacheResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

@Service
@Slf4j
public class CacheService {

    @Value("${cache.ttl.minute.inmillis}")
    private int ttlminutes;

    @Autowired
    CacheManager<Map<LocalDate,Double>> cacheManager;

    public void StoreBPIIntoCache(Map<String,Double> bpi, String currency) {
        log.info("storing the bpis into cache for for the currency:{}",currency);
        try {
            Map<LocalDate, Double> cachebpis = cacheManager.get(currency);
            if (cachebpis == null) {
                cachebpis = new TreeMap<>();
            }
            for (Map.Entry<String, Double> entries : bpi.entrySet()) {
                cachebpis.put(LocalDate.parse(entries.getKey()), entries.getValue());
            }
            cacheManager.put(currency,cachebpis,ttlminutes);
        } catch (Exception ex){
            log.error("error occured while adding the bpis into the cache with exception",ex);
        }
    }

    public CacheResponse retrieveBPIFromCache(String startDate, String endDate, String currency){
        log.info("retrieving the bpis from the cache for the startDate:{}, endDate:{}, currency:{}",
                startDate,endDate,currency);
        CacheResponse cacheResponse = new CacheResponse();
        Map<String,Double> retrievedCachedbpis = new TreeMap<>();
        Map<LocalDate,Double> cachedbpis = cacheManager.get(currency);
        if(cachedbpis==null) cachedbpis = new TreeMap<>();
        LocalDate currentDate = LocalDate.parse(startDate);
        LocalDate lastDate = LocalDate.parse(endDate);
        while(!currentDate.isAfter(lastDate)){
            if(cachedbpis.containsKey(currentDate)) {
                retrievedCachedbpis.put(currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
                        cachedbpis.get(currentDate));
                currentDate = currentDate.plusDays(1);
            } else break;
        }
        if(currentDate.isAfter(lastDate)) {
            log.debug("Complete bpis found in the cache for the startDate:{}, endDate:{}, currency:{}",
                    startDate,endDate,currency);
            cacheResponse.setCompleteData(true);
        }else {
            cacheResponse.setCompleteData(false);
            cacheResponse.setStartDate(currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        cacheResponse.setBpis(retrievedCachedbpis);
        return cacheResponse;
    }
}
