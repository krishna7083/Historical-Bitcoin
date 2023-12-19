package com.bitcoinpriceprovider.cucumberglue;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/BitCoinPriceProviderIntegrationTest.feature")
public class OpenWeatherAPIRunner {
}
