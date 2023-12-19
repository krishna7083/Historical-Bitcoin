package com.bitcoinpriceprovider.restclient;

import com.bitcoinpriceprovider.constants.APIConstants;
import com.bitcoinpriceprovider.model.response.CoinDeskHistoricAPIResponse;
import com.bitcoinpriceprovider.model.response.CurrencyInfo;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = APIConstants.COINDESK_API, url = "${coindesk.api.url}", fallback = CoinDeskAPIProxyFallback.class)
@Component
public interface CoinDeskAPIProxy {

    @GetMapping(value = APIConstants.COINDESK_HISTORIC_PRICE_V1_API, consumes = "application/javascript")
    CoinDeskHistoricAPIResponse getHistoricalPriceIndex(@RequestParam(APIConstants.STARTDATE) String startDate,
                                           @RequestParam(APIConstants.ENDDATE) String endDate,
                                           @RequestParam(APIConstants.CURRENCY) String currency);

    @GetMapping(APIConstants.COINDESK_SUPPORTED_CURRENCIES_API_V1)
    List<CurrencyInfo> getCurrenciesSupported();

}
