Feature: Random Integer
  Check if random integer values are generated correctly

  Background:
    Given Open Chrome Browser
    When Open Random Integer main page

  Scenario: Check random integers generator
    Given Open Random Integer page
    Then Accept Cookies
    When populate min value with "5" and max value with "25"
#    Then click on Get Numbers button
#    Then all generated numbers are with min value = "5" and max value = "25"
    Then Close browser