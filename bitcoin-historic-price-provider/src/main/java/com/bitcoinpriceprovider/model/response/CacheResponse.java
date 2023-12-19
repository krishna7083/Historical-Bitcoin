package com.bitcoinpriceprovider.model.response;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CacheResponse {
    boolean isCompleteData;
    String startDate;
    Map<String,Double> bpis;
}
