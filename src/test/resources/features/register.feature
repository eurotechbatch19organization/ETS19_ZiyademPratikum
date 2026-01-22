Feature: Register Scenarios

  Background:
    Given The user is on the main page

  Scenario: Successful Registration with Valid Data -TC02-[ZYD19-2]
    When The user navigates to the registration page
    And The user opens the email page
    And The user enters the  email address
    Then The email should be displayed in the email input field
    And The user clicks to register button
    Then The user checks the inbox and refreshes the page
    And The user opens the registration email
    And The user opens the password reset link in a new tab
    And The user switches to the password creation page
    When The user enters and submits a new password
    Then The user verifies password creation page is displayed

  Scenario: Registration with Empty Required Fields -TC03-[ZYD19-2]
    When The user navigates to the registration page
    Then The user leave required fields empty
    And The user clicks to register button
    Then The user verifies that the warning pop-up is displayed

  Scenario Outline: Registration with Invalid Email Format-1 -TC04-[ZYD19-2]
    When The user navigates to the registration page
    Then The user email field enters "<email>"
    And The user clicks to register button
    Then The user verifies that the warning email pop-up is displayed
    Examples:
      | email              |
      | trialAddress       |
      | @trialusername.com |
      | username@.com      |

  Scenario Outline: Registration with Invalid Email Format-2 -TC04-[ZYD19-2]
    When The user navigates to the registration page
    Then The user enters "<email>" into email field
    And The user clicks to register button
    Then The user error message should be displayed
    Examples:
      | email        |
      | username@com |
      | user@gmail   |

  Scenario: Registration with Duplicate Email -TC05-[ZYD19-2]
    When The user navigates to the registration page
    Then The user enter an email address that is already registered
    And The user clicks to register button
    Then The user error message should be displayed
