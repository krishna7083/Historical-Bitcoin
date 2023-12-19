package com.bitcoinpriceprovider.controller;


import com.bitcoinpriceprovider.BitCoinHistoricPriceProviderApplication;
import com.bitcoinpriceprovider.constants.APIConstants;
import com.bitcoinpriceprovider.model.request.BitCoinPricesIndexRequest;
import com.bitcoinpriceprovider.model.response.BitCoinPricesIndexResponse;
import com.bitcoinpriceprovider.service.BitCoinHistoricPriceProviderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to handle API requests
 */
@RestController
@CrossOrigin(origins ="*")
@Slf4j
public class BitCoinHistoricPriceProviderController {

    @Autowired
    BitCoinHistoricPriceProviderService bitCoinHistoricPriceProviderService;

    /**
     * Retrieve data based on the provided parameters.
     *
     * @param bitCoinPricesIndexRequest The request object containing start date, end date, and optional currency.
     * @return A message indicating the success of data processing.
     */
    @PostMapping(APIConstants.RETRIEVE_BITCOIN_PRICES)
    private ResponseEntity<BitCoinPricesIndexResponse> retrieveBitcoinPrices(
            @Valid @RequestBody BitCoinPricesIndexRequest bitCoinPricesIndexRequest){
        return new ResponseEntity<BitCoinPricesIndexResponse>(bitCoinHistoricPriceProviderService.
                        getBPIHistoric(bitCoinPricesIndexRequest), HttpStatus.OK);
    }
}
