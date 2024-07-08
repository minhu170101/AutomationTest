Feature: feature to test add user functionality

  Scenario Outline: Add a users with different criteria
    Given user is on add user page
    When user entering "<username>", "<userRole>", "<employee>", "<status>", "<password>" and "<confirmPassword>"
    And clicks on save button
    Then system should navigate to view user page

    Examples: 
      | username  | userRole | employee | status  | password  | confirmPassword |
      | admin1235 | Admin    | Ravi M B | Enabled | admin1235 | admin1235       |
      | admin123  | Admin    | Ravi M B | Enabled | admin123  | admin123        |
