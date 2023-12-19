package com.bitcoinpriceprovider.model.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BitCoinPricesIndexResponse {
    private String errocode;
    private String errormessage;
    private Map<String, Double> bpi;
    private PriceAndDate low;
    private PriceAndDate high;
}
