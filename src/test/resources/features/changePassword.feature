Feature: Change Password Functionality

  Background:
    Given user opens browser
    And user navigates to login page
    When user logs in with valid credentials
    And user clicks account details page
    Then user is on the change password page
@jasmin
  Scenario: TC01 - Three password fields are displayed on change password page
    Then user should see Current Password field
    And user should see New Password field
    And user should see Confirm New Password field
@jasmin
  Scenario: TC02 - Password remains unchanged when all fields are left blank
    When user leaves "Current Password" field blank
    And user leaves "New Password" field blank
    And user leaves "Confirm New Password" field blank
    And user clicks Save Changes button
    Then success message "Kontodetails erfolgreich geändert." should be displayed
    And user should remain on change password page
    And user logs out
    And user should be able to login with original password "TestPass123!"
@jasmin
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
@jasmin
Scenario: TC04 - Password is successfully updated and success message is displayed
  When user enters current password "TestPass123!"
  And user enters new password "NewSecure@Pass456"
  And user enters confirm password "NewSecure@Pass456"
  And user clicks Save Changes button
  Then success message "Kontodetails erfolgreich geändert." should be displayed
  And password should be updated to "NewSecure@Pass456"
  And user logs out
  And user should be able to login with new password "NewSecure@Pass456"
  And user clicks account details page
  And user resets password back to original "TestPass123!"
  And user logs out
  And user should be able to login with original password "TestPass123!"
@jasmin
  Scenario: TC05 - User remains logged in after successful password change
    When user enters current password "TestPass123!"
    And user enters new password "NewPassword@789"
    And user enters confirm password "NewPassword@789"
    And user clicks Save Changes button
    Then password should be updated to "NewPassword@789"
    And success message "Kontodetails erfolgreich geändert." should be displayed
    And user session should remain active
    And user logs out
    And user should be able to login with new password "NewPassword@789"
    And user clicks account details page
    And user resets password back to original "TestPass123!"
    And user logs out
    And user should be able to login with original password "TestPass123!"
    



















