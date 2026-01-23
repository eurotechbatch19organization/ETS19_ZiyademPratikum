Feature: Navigation bar visibility

  Background:
    Given the user is on the home page

  Scenario: Navigation bar is displayed at the top of all pages-TC001[ZYD19-5]
    Then the navigation bar should be visible at the top of the page
    When the user navigates to another page
    Then the navigation bar should still be visible at the top of the page
  @wip
  Scenario: All main categories are displayed in the navigation bar-TC002[ZYD19-5]
    Then all main categories should be visible in the navigation bar
