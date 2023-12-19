package com.bitcoinpriceprovider.service;

import org.springframework.stereotype.Service;

import java.util.Map;

public class CurrenyService {

    private Map<String, String> currencyMap;

    public CurrenyService (Map<String, String> currencyMap) {
        this.currencyMap = currencyMap;
    }

    public boolean isCurrencyPresent(String currency) {
        return currencyMap.containsKey(currency);
    }

    public String getCountryByCurrency(String currency) {
        return currencyMap.get(currency);
    }
}
