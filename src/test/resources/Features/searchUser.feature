Feature: feature to test search user functionality

  Scenario Outline: Search for users by different criteria
    Given user is on search page
    When user entering "<username>", "<userRole>", "<employee>" and "<status>"
    And clicks on search button
    Then system should display the search results

    Examples: 
      | username | userRole | employee | status  |
      | admin    |          |          |         |
      | admin    | Admin    |          |         |
      |          |          | Sanket   |         |
      |          |          |          | Enabled |
      | admin    | Admin    | Sanket   | Enabled |
      | sadasdqw | Admin    | asdwqewq | Enabled |
