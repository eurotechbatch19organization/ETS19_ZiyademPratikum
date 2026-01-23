
Feature: Navigation bar functionality

  Background:
    Given the user is on the home page

  Scenario: Navigation bar is displayed at the top of all pages - TC001
    Then the navigation bar should be visible
    When the user navigates to another page
    Then the navigation bar should still be visible

  Scenario: All main categories are displayed in the navigation bar - TC002
    Then all main categories should be displayed

  Scenario: Dropdown menus are visible on hover - TC003
    When the user hovers over all categories with submenus
    Then dropdown menus should be visible

  Scenario: Clicking categories without submenu redirects correctly - TC004
    When the user clicks on each category without submenu
    Then the related category pages should be opened

  Scenario: Subcategory links redirect correctly - TC005
    When the user hovers over a category with submenu
    And clicks the first subcategory link
    Then a valid product listing or landing page should be opened


