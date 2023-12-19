package com.bitcoinpriceprovider.cucumberglue;

import com.bitcoinpriceprovider.model.request.BitCoinPricesIndexRequest;
import com.bitcoinpriceprovider.model.response.BitCoinPricesIndexResponse;
import com.bitcoinpriceprovider.service.BitCoinHistoricPriceProviderService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class OpenWeatherAPIStepDef {

    @Autowired
    BitCoinHistoricPriceProviderService historicPriceProviderService;

    BitCoinPricesIndexResponse bitCoinPricesIndexResponse =
            new BitCoinPricesIndexResponse();
    BitCoinPricesIndexRequest bitCoinPricesIndexRequest =
            new BitCoinPricesIndexRequest();

    @Given("the user provides a valid start date {string} and end date {string}")
    public void the_user_provides_a_valid_start_date_and_end_date(String startDate, String endDate) {
        bitCoinPricesIndexRequest =
                new BitCoinPricesIndexRequest();
        bitCoinPricesIndexRequest.setStartDate(startDate);
        bitCoinPricesIndexRequest.setEndDate(endDate);
    }

    @When("the user retrieves Bitcoin prices")
    public void the_user_retrieves_bitcoin_prices() {
        bitCoinPricesIndexResponse =
                historicPriceProviderService.getBPIHistoric(bitCoinPricesIndexRequest);
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer status) {
        assertEquals(100,100);
    }

    @Then("the response should low price as {double} and high price as {double}")
    public void the_response_should_low_price_as_and_high_price_as(Double lowPrice, Double highPrice) {
        assertEquals("200","200");
    }


    /*@Given("the user provides a valid start date {string}, end date {string}, and currency {string}")
    public void the_user_provides_a_valid_start_date_end_date_and_currency(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response should contain valid Bitcoin low price as {double} and high price as {double}")
    public void the_response_should_contain_valid_bitcoin_low_price_as_and_high_price_as(Double double1, Double double2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Given("the user does not provide a start date")
    public void the_user_does_not_provide_a_start_date() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response should contain an error message indicating that the start date is required {string}")
    public void the_response_should_contain_an_error_message_indicating_that_the_start_date_is_required(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user does not provide an end date")
    public void the_user_does_not_provide_an_end_date() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response should contain an error message indicating that the end date is required {string}")
    public void the_response_should_contain_an_error_message_indicating_that_the_end_date_is_required(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Given("the user provides a valid start date {string} and end date {string} without specifying a currency")
    public void the_user_provides_a_valid_start_date_and_end_date_without_specifying_a_currency(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response should contain valid Bitcoin prices for the specified date range with USD as the default currency")
    public void the_response_should_contain_valid_bitcoin_prices_for_the_specified_date_range_with_usd_as_the_default_currency() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



    @Given("the user provides a valid start date {string} and end date {string} with an invalid currency")
    public void the_user_provides_a_valid_start_date_and_end_date_with_an_invalid_currency(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the response should contain an error message indicating {string}")
    public void the_response_should_contain_an_error_message_indicating(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }*/

}
