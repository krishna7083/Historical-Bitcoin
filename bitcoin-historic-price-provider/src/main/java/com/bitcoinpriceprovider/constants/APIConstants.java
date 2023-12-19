package com.bitcoinpriceprovider.constants;

public class APIConstants {
    private APIConstants() {
        throw new IllegalStateException("APIConstants class");
    }
    public static final String RETRIEVE_BITCOIN_PRICES = "/retrieve-bitcoin-price-indices";
    public static final String ERRORCODE_SUCCESS = "00";
    public static final String SUCCESS = "SUCCESS";
    public static final String COINDESK_API = "coindesk-api";
    public static final String COINDESK_HISTORIC_PRICE_V1_API = "v1/bpi/historical/close.json";
    public static final String COINDESK_SUPPORTED_CURRENCIES_API_V1 = "/v1/bpi/supported-currencies.json";
    public static final String STARTDATE = "start";
    public static final String ENDDATE = "end";
    public static final String CURRENCY = "currency";
    public static final String CURRENCY_NOT_SUPPORT_ERROR_CODE = "01";
    public static final String CURRENT_NOT_SUPPORT_MESSAGE = "currency not supported by system";
}
