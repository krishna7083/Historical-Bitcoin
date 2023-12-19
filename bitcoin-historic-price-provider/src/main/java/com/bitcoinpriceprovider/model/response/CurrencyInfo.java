package com.bitcoinpriceprovider.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CurrencyInfo {
    private String currency;
    private String country;
}
