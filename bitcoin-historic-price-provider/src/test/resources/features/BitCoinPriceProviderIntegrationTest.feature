Feature: Retrieve Bitcoin Prices

  Scenario: Retrieve Bitcoin prices with valid input
    Given the user provides a valid start date "2019-12-25" and end date "2020-01-10"
    When the user retrieves Bitcoin prices
    Then the response status code should be 200
    And the response should low price as 6961.5683 and high price as 8189.265

  Scenario: Retrieve Bitcoin prices with valid input and specified currency
    Given the user provides a valid start date "2019-12-25", end date "2020-01-10", and currency "EUR"
    When the user retrieves Bitcoin prices
    Then the response status code should be 200
    And the response should contain valid Bitcoin low price as 6231.3416 and high price as 7363.7871

  Scenario: Retrieve Bitcoin prices with invalid currency
    Given the user provides a valid start date "2023-01-01" and end date "2023-02-01" with an invalid currency
    When the user retrieves Bitcoin prices
    Then the response status code should be 200
    And the response should contain an error message indicating "currency not supported by system"

#  Scenario: Retrieve Bitcoin prices with missing start date
#    Given the user does not provide a start date
#    When the user retrieves Bitcoin prices
#    Then the response status code should be 400
#    And the response should contain an error message indicating that the start date is required "Start date is required"
#
#  Scenario: Retrieve Bitcoin prices with missing end date
#    Given the user does not provide an end date
#    When the user retrieves Bitcoin prices
#    Then the response status code should be 400
#    And the response should contain an error message indicating that the end date is required "End date is required"