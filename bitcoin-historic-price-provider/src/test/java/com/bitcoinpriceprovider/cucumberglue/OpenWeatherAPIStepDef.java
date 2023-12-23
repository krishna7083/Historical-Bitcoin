package com.bitcoinpriceprovider.cucumberglue;

import com.bitcoinpriceprovider.controller.BitCoinHistoricPriceProviderController;
import com.bitcoinpriceprovider.model.request.BitCoinPricesIndexRequest;
import com.bitcoinpriceprovider.model.response.BitCoinPricesIndexResponse;
import com.bitcoinpriceprovider.service.BitCoinHistoricPriceProviderService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class OpenWeatherAPIStepDef {
    @Autowired
    BitCoinHistoricPriceProviderController bitCoinHistoricPriceProviderController;
    ResponseEntity<BitCoinPricesIndexResponse> response;

    BitCoinPricesIndexRequest bitCoinPricesIndexRequest =
            new BitCoinPricesIndexRequest();

    String defaultStartDate = "2019-12-25";
    String defaultEndDate = "2020-01-10";
    String defaultCurrency = "USD";

    @Given("the user provides a valid start date {string} and end date {string}")
    public void the_user_provides_a_valid_start_date_and_end_date(String startDate, String endDate) {
        bitCoinPricesIndexRequest =
                new BitCoinPricesIndexRequest();
        bitCoinPricesIndexRequest.setStartDate(startDate);
        bitCoinPricesIndexRequest.setEndDate(endDate);
    }

    @When("the user retrieves Bitcoin prices")
    public void the_user_retrieves_bitcoin_prices() {
//        bitCoinPricesIndexResponse =
//                historicPriceProviderService.getBPIHistoric(bitCoinPricesIndexRequest);
        response =
                bitCoinHistoricPriceProviderController.retrieveBitcoinPrices(bitCoinPricesIndexRequest);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int status) {
        assertEquals(response.getStatusCode().value(),status);
    }

    @Then("the response should low price as {double} and high price as {double}")
    public void the_response_should_low_price_as_and_high_price_as(Double lowPrice, Double highPrice) {
        assertEquals(response.getBody().getLow().getPrice(),lowPrice);
        assertEquals(response.getBody().getHigh().getPrice(),highPrice);
    }


    @Given("the user provides a valid start date {string}, end date {string}, and currency {string}")
    public void the_user_provides_a_valid_start_date_end_date_and_currency(String startDate, String endDate, String currency) {
        bitCoinPricesIndexRequest =
                new BitCoinPricesIndexRequest();
        bitCoinPricesIndexRequest.setStartDate(startDate);
        bitCoinPricesIndexRequest.setEndDate(endDate);
        bitCoinPricesIndexRequest.setCurrency(currency);
    }

    @Then("the response should contain valid Bitcoin low price as {double} and high price as {double}")
    public void the_response_should_contain_valid_bitcoin_low_price_as_and_high_price_as(Double lowPrice, Double highPrice) {
        assertEquals(response.getBody().getLow().getPrice(),lowPrice);
        assertEquals(response.getBody().getHigh().getPrice(),highPrice);
    }


    @Given("the user does not provide a start date")
    public void the_user_does_not_provide_a_start_date() {
        bitCoinPricesIndexRequest.setStartDate(null);
        bitCoinPricesIndexRequest.setEndDate(defaultEndDate);
    }

    @Then("the response should contain an error message indicating that the start date is required {string}")
    public void the_response_should_contain_an_error_message_indicating_that_the_start_date_is_required(String errorMessage) {
        assertTrue(response.getBody().toString().contains(errorMessage));
    }

    @Given("the user does not provide an end date")
    public void the_user_does_not_provide_an_end_date() {
        bitCoinPricesIndexRequest.setStartDate(defaultStartDate);
        bitCoinPricesIndexRequest.setEndDate(null);
    }

    @Then("the response should contain an error message indicating that the end date is required {string}")
    public void the_response_should_contain_an_error_message_indicating_that_the_end_date_is_required(String errorMessage) {
        assertTrue(response.getBody().toString().contains(errorMessage));
    }

    @Given("the user provides a valid start date {string} and end date {string} with an invalid currency")
    public void the_user_provides_a_valid_start_date_and_end_date_with_an_invalid_currency(String startDate, String endDate) {
        bitCoinPricesIndexRequest.setStartDate(startDate);
        bitCoinPricesIndexRequest.setEndDate(endDate);
        bitCoinPricesIndexRequest.setCurrency("INVALID");
    }

    @Then("the response should contain an error message indicating {string}")
    public void the_response_should_contain_an_error_message_indicating(String errorMessage) {
        assertEquals(response.getBody().getErrormessage(),errorMessage);
    }

}
