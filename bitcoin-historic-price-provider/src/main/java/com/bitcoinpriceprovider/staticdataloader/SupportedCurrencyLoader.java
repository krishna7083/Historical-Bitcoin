package com.bitcoinpriceprovider.staticdataloader;

import com.bitcoinpriceprovider.model.response.CurrencyInfo;
import com.bitcoinpriceprovider.restclient.CoinDeskAPIProxy;
import com.bitcoinpriceprovider.service.CurrenyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SupportedCurrencyLoader {

    @Autowired
    CoinDeskAPIProxy coinDeskAPIProxy;

    @Bean
    public CurrenyService currencyService() {
        return new CurrenyService(fetchCurrencyData());
    }

    private Map<String, String> fetchCurrencyData() {
        List<CurrencyInfo> currencyInfoList =
                coinDeskAPIProxy.getCurrenciesSupported();
        Map<String,String> currencies = new HashMap<>();
        for(CurrencyInfo currencyInfo: currencyInfoList){
            currencies.put(currencyInfo.getCurrency(), currencyInfo.getCountry());
        }
        return currencies;
    }
}