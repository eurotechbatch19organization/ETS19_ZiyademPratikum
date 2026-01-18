Feature:Login Scenarios

  Scenario: First Login
    Given User is on the main login page
    When The user enters valid email and password and clicks the Login button
    Then The user should see the main page