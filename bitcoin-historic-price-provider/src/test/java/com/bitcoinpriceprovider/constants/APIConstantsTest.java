package com.bitcoinpriceprovider.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class APIConstantsTest {

    @Test
    void testRetrieveBitcoinPricesEndpoint() {
        assertEquals("/retrieve-bitcoin-price-indices", APIConstants.RETRIEVE_BITCOIN_PRICES);
    }

    @Test
    void testSuccessErrorCode() {
        assertEquals("00", APIConstants.ERRORCODE_SUCCESS);
    }

    @Test
    void testSuccessMessage() {
        assertEquals("SUCCESS", APIConstants.SUCCESS);
    }

    @Test
    void testCoindeskApi() {
        assertEquals("coindesk-api", APIConstants.COINDESK_API);
    }

    @Test
    void testCoindeskHistoricPriceApi() {
        assertEquals("v1/bpi/historical/close.json", APIConstants.COINDESK_HISTORIC_PRICE_V1_API);
    }

    @Test
    void testCoindeskSupportedCurrenciesApi() {
        assertEquals("/v1/bpi/supported-currencies.json", APIConstants.COINDESK_SUPPORTED_CURRENCIES_API_V1);
    }

    @Test
    void testStartDateConstant() {
        assertEquals("start", APIConstants.STARTDATE);
    }

    @Test
    void testEndDateConstant() {
        assertEquals("end", APIConstants.ENDDATE);
    }

    @Test
    void testCurrencyConstant() {
        assertEquals("currency", APIConstants.CURRENCY);
    }

    @Test
    void testCurrencyNotSupportErrorCode() {
        assertEquals("01", APIConstants.CURRENCY_NOT_SUPPORT_ERROR_CODE);
    }

    @Test
    void testCurrencyNotSupportMessage() {
        assertEquals("currency not supported by system", APIConstants.CURRENT_NOT_SUPPORT_MESSAGE);
    }
}

