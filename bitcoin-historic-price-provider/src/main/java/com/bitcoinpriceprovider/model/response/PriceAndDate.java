package com.bitcoinpriceprovider.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PriceAndDate {
    private String date;
    private Double price;
}
