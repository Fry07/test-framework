Feature: Random Integer
  Check if random integer values are generated correctly

  Background:
    Given Open Chrome Browser
    When Open Random Integer main page
    Then Accept Cookies

  Scenario: Check random integers generator
    Given Open Random Integer page
    When populate min value with "5" and max value with "25"
    Then click on Get Numbers button
    Then all generated numbers are with min value = "5" and max value = "25"
    And Close Driver