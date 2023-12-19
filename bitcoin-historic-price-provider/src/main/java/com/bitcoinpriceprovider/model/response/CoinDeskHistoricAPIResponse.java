package com.bitcoinpriceprovider.model.response;

import lombok.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ResponseBody
public class CoinDeskHistoricAPIResponse {
    private Map<String, Double> bpi;
}
