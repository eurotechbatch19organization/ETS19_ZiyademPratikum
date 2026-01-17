Feature: Change Password Functionality

  Background:
    Given user opens browser
    And user navigates to "https://ziyadem.de/"
    When user logs in with valid credentials
    And user clicks account details page
    Then user is on the change password page
@wip
  Scenario: TC01 - Three password fields are displayed on change password page
    Then user should see Current Password field
    And user should see New Password field
    And user should see Confirm New Password field

