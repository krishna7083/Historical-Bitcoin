package com.bitcoinpriceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BitCoinHistoricPriceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitCoinHistoricPriceProviderApplication.class, args);
	}

}
