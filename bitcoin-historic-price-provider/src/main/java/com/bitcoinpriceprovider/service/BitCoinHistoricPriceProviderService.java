package com.bitcoinpriceprovider.service;

import com.bitcoinpriceprovider.model.request.BitCoinPricesIndexRequest;
import com.bitcoinpriceprovider.model.response.BitCoinPricesIndexResponse;
import org.springframework.stereotype.Component;

@Component
public interface BitCoinHistoricPriceProviderService {

    public BitCoinPricesIndexResponse getBPIHistoric(BitCoinPricesIndexRequest bitCoinPricesIndexRequest);
}
