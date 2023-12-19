Feature: Retrieve Bitcoin Prices

  Scenario: Retrieve Bitcoin prices with valid input
    Given the user provides a valid start date "2019-12-25" and end date "2020-01-02"
    When the user retrieves Bitcoin prices
    Then the response status code should be 200
    And the response should low price as 2014.35 and high price as 2030.15

#  Scenario: Retrieve Bitcoin prices with valid input and specified currency
#    Given the user provides a valid start date "2023-01-01", end date "2023-02-01", and currency "EUR"
#    When the user retrieves Bitcoin prices
#    Then the response status code should be 200
#    And the response should contain valid Bitcoin low price as 2014.35 and high price as 2030.15
#
#  Scenario: Retrieve Bitcoin prices with missing start date
#    Given the user does not provide a start date
#    When the user retrieves Bitcoin prices
#    Then the response status code should be 400
#    And the response should contain an error message indicating that the start date is required "startdate required"
#
#  Scenario: Retrieve Bitcoin prices with missing end date
#    Given the user does not provide an end date
#    When the user retrieves Bitcoin prices
#    Then the response status code should be 400
#    And the response should contain an error message indicating that the end date is required "enddate required"
#
#  Scenario: Retrieve Bitcoin prices with valid date range and missing currency
#    Given the user provides a valid start date "2023-01-01" and end date "2023-02-01" without specifying a currency
#    When the user retrieves Bitcoin prices
#    Then the response status code should be 200
#    And the response should contain valid Bitcoin prices for the specified date range with USD as the default currency
#
#  Scenario: Retrieve Bitcoin prices with invalid currency
#    Given the user provides a valid start date "2023-01-01" and end date "2023-02-01" with an invalid currency
#    When the user retrieves Bitcoin prices
#    Then the response status code should be 400
#    And the response should contain an error message indicating "curreny not support"
