Feature: feature to test login functionality

  Scenario Outline: Check login is successful with valid credentials
    Given user is logged in to the system
    When user entering "<username>" and "<password>"
    And clicks on login button
    Then user should be redirected to "<url>" if successful
    And user should see message if unsuccessful

    Examples: 
      | username | password | url                                                                     |
      | Admin    | admin123 | https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index |
      | admin123 | admin123 | https://opensource-demo.orangehrmlive.com/web/index.php/auth/login      |
      | admin111 | admin111 | https://opensource-demo.orangehrmlive.com/web/index.php/auth/login      |
      | Admin    |          | https://opensource-demo.orangehrmlive.com/web/index.php/auth/login      |
      |          | admin123 | https://opensource-demo.orangehrmlive.com/web/index.php/auth/login      |
      |          |          | https://opensource-demo.orangehrmlive.com/web/index.php/auth/login      |
