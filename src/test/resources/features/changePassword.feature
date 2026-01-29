
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
    And user verifies password remains unchanged

  Scenario: TC03 - Password is successfully changed with valid data
    When user enters current password "TestPass123!"
    And user enters new password "NewSecure@Pass456"
    And user enters confirm password "NewSecure@Pass456"
    And user clicks Save Changes button
    Then success message "Kontodetails erfolgreich geändert." should be displayed
    And user logs out
    And user should be able to login with new password "NewSecure@Pass456"
    And user completes password reset cleanup with new password "NewSecure@Pass456"

Scenario: TC04 - Password is successfully updated and success message is displayed
  When user enters current password "TestPass123!"
  And user enters new password "NewSecure@Pass456"
  And user enters confirm password "NewSecure@Pass456"
  And user clicks Save Changes button
  Then success message "Kontodetails erfolgreich geändert." should be displayed
  And password should be updated to "NewSecure@Pass456"
  And user completes password reset cleanup with new password "NewSecure@Pass456"

  Scenario: TC05 - User remains logged in after successful password change
    When user enters current password "TestPass123!"
    And user enters new password "NewPassword@789"
    And user enters confirm password "NewPassword@789"
    And user clicks Save Changes button
    Then password should be updated to "NewPassword@789"
    And success message "Kontodetails erfolgreich geändert." should be displayed
    And user session should remain active
    And user completes password reset cleanup with new password "NewPassword@789"

  Scenario: TC06 - Password change fails when incorrect current password is entered
    When user enters current password "WrongPassword123!"
    And user enters new password "NewSecure@Pass456"
    And user enters confirm password "NewSecure@Pass456"
    And user clicks Save Changes button
    Then error message "Dein derzeitiges Passwort ist nicht korrekt." should be displayed
    And user should remain on change password page
    And user should remain on change password page
    And user verifies password remains unchanged

  Scenario: TC08 - Error message when new password and confirm password don't match
    When user enters current password "TestPass123!"
    And user enters new password "NewPassword123!@"
    And user enters confirm password "DifferentPass123!@"
    And user clicks Save Changes button
    Then error message "Die neuen Passwörter stimmen nicht überein." should be displayed
    And user should remain on change password page
    And user verifies password remains unchanged

  Scenario: TC09 - User cannot login with old password after successful password change
    When user enters current password "TestPass123!"
    And user enters new password "NewSecure@Pass456"
    And user enters confirm password "NewSecure@Pass456"
    And user clicks Save Changes button
    Then success message "Kontodetails erfolgreich geändert." should be displayed
    And user verifies old password fails and resets to original with new password "NewSecure@Pass456"
@bug
Scenario: TC07 - BUG: System accepts 10-character password
    When user enters current password "TestPass123!"
    And user enters new password "Strong2024"
    And user enters confirm password "Strong2024"
    And user attempts to bypass validation and click Save Changes
    Then system accepts invalid password due to bug
    And user completes password reset cleanup with new password "Strong2024"
















