Feature: Change Password Functionality

  Background:
    Given user opens browser
    And user navigates to login page
    When user logs in with valid credentials
    And user clicks account details page
    Then user is on the change password page
@wip
  Scenario: TC01 - Three password fields are displayed on change password page
    Then user should see Current Password field
    And user should see New Password field
    And user should see Confirm New Password field
@wip
  Scenario: TC02 - Password remains unchanged when all fields are left blank
    When user leaves "Current Password" field blank
    And user leaves "New Password" field blank
    And user leaves "Confirm New Password" field blank
    And user clicks Save Changes button
    Then success message "Kontodetails erfolgreich geändert." should be displayed
    And user should remain on change password page
    And user logs out
    And user should be able to login with original password "TestPass123!"
@wip
  Scenario: TC03 - Password is successfully changed with valid data
    When user enters current password "TestPass123!"
    And user enters new password "NewSecure@Pass456"
    And user enters confirm password "NewSecure@Pass456"
    And user clicks Save Changes button
    Then success message "Kontodetails erfolgreich geändert." should be displayed
    And user logs out
    And user should be able to login with new password "NewSecure@Pass456"
    And user clicks account details page
    And user resets password back to original "TestPass123!"
    And user logs out
    And user should be able to login with original password "TestPass123!"

    



















