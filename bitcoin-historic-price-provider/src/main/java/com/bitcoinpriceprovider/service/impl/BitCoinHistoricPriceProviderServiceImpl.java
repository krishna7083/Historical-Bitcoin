package com.bitcoinpriceprovider.service.impl;

import com.bitcoinpriceprovider.constants.APIConstants;
import com.bitcoinpriceprovider.model.request.BitCoinPricesIndexRequest;
import com.bitcoinpriceprovider.model.response.BitCoinPricesIndexResponse;
import com.bitcoinpriceprovider.model.response.CacheResponse;
import com.bitcoinpriceprovider.model.response.CoinDeskHistoricAPIResponse;
import com.bitcoinpriceprovider.model.response.PriceAndDate;
import com.bitcoinpriceprovider.restclient.CoinDeskAPIProxy;
import com.bitcoinpriceprovider.service.BitCoinHistoricPriceProviderService;
import com.bitcoinpriceprovider.service.CacheService;
import com.bitcoinpriceprovider.service.CurrenyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

import static com.bitcoinpriceprovider.constants.APIConstants.*;

@Service
@Slf4j
public class BitCoinHistoricPriceProviderServiceImpl implements BitCoinHistoricPriceProviderService {

    @Autowired
    CoinDeskAPIProxy coinDeskAPIProxy;

    @Autowired
    CurrenyService currenyService;
    @Autowired
    CacheService cacheService;

    @Value("${default.currency}")
    String DefaultCurrency;

    private static final String LOW = "low";
    private static final String HIGH = "high";

    @Override
    public BitCoinPricesIndexResponse getBPIHistoric(BitCoinPricesIndexRequest bitCoinPricesIndexRequest) {
        BitCoinPricesIndexResponse bitCoinPricesIndexResponse = new BitCoinPricesIndexResponse();
        String currency = bitCoinPricesIndexRequest.getCurrency()!=null
                ? bitCoinPricesIndexRequest.getCurrency() : DefaultCurrency;
        String startDate = bitCoinPricesIndexRequest.getStartDate();
        String endDate = bitCoinPricesIndexRequest.getEndDate();
        // to check if given currency is supported in the system or not
        if(!currenyService.isCurrencyPresent(currency)){
                log.info("provided currency: {} not supported by the system",currency);
               bitCoinPricesIndexResponse.setErrocode(CURRENCY_NOT_SUPPORT_ERROR_CODE);
               bitCoinPricesIndexResponse.setErrormessage(CURRENT_NOT_SUPPORT_MESSAGE);
               return bitCoinPricesIndexResponse;
        }
        // check in cache, before hitting actual API if data is present
        CacheResponse cacheResponse = cacheService.retrieveBPIFromCache(startDate,endDate,currency);
        if(cacheResponse.isCompleteData()){
            log.info("complete bpis has been found in cache for the startDate: {}, endDate: {}, currency: {}",
                    startDate, endDate, currency);
            bitCoinPricesIndexResponse.setErrocode(ERRORCODE_SUCCESS);
            bitCoinPricesIndexResponse.setErrormessage(SUCCESS);
            bitCoinPricesIndexResponse.setBpi(cacheResponse.getBpis());
            return bitCoinPricesIndexResponse;
        }
        // if found partial data is avaailable for the few dates, then hit the API only for the missing range
        Map<String,Double> partialCachedBpis = cacheResponse.getBpis();
        startDate = cacheResponse.getStartDate();
        log.info("retrieving the bpis from the API for the startDate:{}, endDate:{}, currency:{}",
                startDate,endDate,currency);
        CoinDeskHistoricAPIResponse coinDeskHistoricAPIResponse =
                coinDeskAPIProxy.getHistoricalPriceIndex(startDate,endDate,currency);
        cacheService.StoreBPIIntoCache(coinDeskHistoricAPIResponse.getBpi(),currency);
        Map<String,Double> completeBpis = new TreeMap<>(partialCachedBpis);
        completeBpis.putAll(coinDeskHistoricAPIResponse.getBpi());
        bitCoinPricesIndexResponse.setErrocode(ERRORCODE_SUCCESS);
        bitCoinPricesIndexResponse.setErrormessage(SUCCESS);
        bitCoinPricesIndexResponse.setBpi(completeBpis);
        bitCoinPricesIndexResponse.setHigh(retrieveHighOrLow(completeBpis,HIGH));
        bitCoinPricesIndexResponse.setLow(retrieveHighOrLow(completeBpis,LOW));
        return bitCoinPricesIndexResponse;
    }

    private PriceAndDate retrieveHighOrLow(Map<String,Double> bpi, String ops) {
        if(ops.equalsIgnoreCase(LOW)){
            Map.Entry<String, Double> minEntry = null;
            for (Map.Entry<String, Double> entry : bpi.entrySet()) {
                if (minEntry == null || entry.getValue() < minEntry.getValue()) {
                    minEntry = entry;
                }
            }
            return new PriceAndDate(minEntry.getKey(), minEntry.getValue());
        }

        Map.Entry<String, Double> maxEntry = null;
        for (Map.Entry<String, Double> entry : bpi.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return new PriceAndDate(maxEntry.getKey(), maxEntry.getValue());
    }
}
