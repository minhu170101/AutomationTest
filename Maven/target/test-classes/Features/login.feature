# Author
# Date
# Description
Feature: feature to test login functionality

  Scenario: Check login is successful with valid credentials
    Given user is on login page
    When user entering email and password
    And clicks on login button
    Then user is navigated to the verify page
