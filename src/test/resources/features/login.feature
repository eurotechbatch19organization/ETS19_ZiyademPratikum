Feature:Login Scenarios

  Background:
    Given The user is on the main login page

  Scenario: Succesfull Login TC01-[ZYD19-1]
    When The user enters valid email
    And The user enters valid password
    Then The user clicks to login button

  Scenario: Wrong Email or Username TC02-[ZYD19-1]
    When The user enters an wrong email or username
    And The user enters valid password
    And The user clicks to login button
    Then The login error message should be displayed

  Scenario: Wrong Password TC03-[ZYD19-1]
    When The user enters valid email
    And The user enters an wrong password
    And The user clicks to login button
    Then The login error message should be displayed

  Scenario: Empty Email or Username Field TC04-[ZYD19-1]
    When The user leaves the email or username field blank
    And The user enters valid password
    And The user clicks to login button
    Then The login error message should be displayed

  Scenario: Empty Password Field TC05-[ZYD19-1]
    When The user enters valid email
    And The user leaves the password field blank
    And The user clicks to login button
    Then The login error message should be displayed

  Scenario: Both Fields TC06-[ZYD19-1]
    When The user leaves the email or username field blank
    And The user leaves the password field blank
    And The user clicks to login button
    Then The login error message should be displayed





