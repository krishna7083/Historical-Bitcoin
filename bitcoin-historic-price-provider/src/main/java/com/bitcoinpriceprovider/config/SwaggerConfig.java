package com.bitcoinpriceprovider.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("BitCoin Historic Price Index Provider")
                        .description("Service provide the historical Bitcoin Price Indices based on the" +
                                " date range provided")
                        .version("1.0"));
    }
}
