package com.bitcoinpriceprovider.restclient;

import com.bitcoinpriceprovider.model.response.CacheResponse;
import com.bitcoinpriceprovider.model.response.CoinDeskHistoricAPIResponse;
import com.bitcoinpriceprovider.model.response.CurrencyInfo;
import com.bitcoinpriceprovider.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CoinDeskAPIProxyFallback implements CoinDeskAPIProxy {

    @Autowired
    CacheService cacheService;

    /*
        Retrieve the data from cache in case coindesk API is not available or not responding.
     */

    @Override
    public CoinDeskHistoricAPIResponse getHistoricalPriceIndex(String startDate, String endDate,
                                                               String currency) {
        CoinDeskHistoricAPIResponse coinDeskHistoricAPIResponse = new CoinDeskHistoricAPIResponse();
        CacheResponse cacheResponse = cacheService.retrieveBPIFromCache(startDate,endDate,
                currency);
        coinDeskHistoricAPIResponse.setBpi(cacheResponse.getBpis());
        return coinDeskHistoricAPIResponse;
    }

    /*
        Retrieve the data supported currencies from the static json file present under
        resource folder of the application in case of fallback.
     */
    @Override
    public List<CurrencyInfo> getCurrenciesSupported() {
        return null;
    }
}
