package com.bitcoinpriceprovider.model.request;

import com.bitcoinpriceprovider.model.validation.DatePattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * Represents the request parameters for the API.
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BitCoinPricesIndexRequest {
    @NotBlank(message = "Start date is required")
    @DatePattern
    private String startDate;

    @NotBlank(message = "End date is required")
    @DatePattern
    private String endDate;

    private String currency;
}
