Feature: Change Password Functionality

  Background:
    Given user opens browser
    And user navigates to login page
    When user logs in with valid credentials
    And user clicks account details page
    Then user is on the change password page

  Scenario: TC01 - Three password fields are displayed on change password page
    Then user should see Current Password field
    And user should see New Password field
    And user should see Confirm New Password field

  Scenario: TC02 - Password remains unchanged when all fields are left blank
    When user leaves "Current Password" field blank
    And user leaves "New Password" field blank
    And user leaves "Confirm New Password" field blank
    And user clicks Save Changes button
    Then success message "Kontodetails erfolgreich geändert." should be displayed
    And user should remain on change password page
    And user logs out
    And user should be able to login with original password "TestPass123!"



















