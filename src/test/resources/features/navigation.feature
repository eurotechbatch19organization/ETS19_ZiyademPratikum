@sld
Feature: Navigation bar functionality
  As a user
  I want to use the navigation bar
  So that I can navigate through categories easily

  Background:
    Given the user is on the home page

  Scenario Outline: Navigation bar is displayed at the top of all pages - TC001[ZYD19-5]
    Given the user is "<userType>"
    Then the navigation bar should be visible
    When the user navigates to another page
    Then the navigation bar should still be visible

    Examples:
      | userType  |
      | guest     |
      | LOGGED_IN |

  Scenario Outline: All main categories are displayed in the navigation bar - TC002[ZYD19-5]
    Given the user is "<userType>"
    Then all main categories should be displayed

    Examples:
      | userType  |
      | guest     |
      | LOGGED_IN |

  Scenario Outline: Dropdown menus are visible on hover - TC003[ZYD19-5]
    Given the user is "<userType>"
    When the user hovers over all categories with submenus
    Then dropdown menus should be visible

    Examples:
      | userType  |
      | guest     |
      | LOGGED_IN |

  Scenario Outline: Clicking categories without submenu redirects correctly - TC004[ZYD19-5]
    Given the user is "<userType>"
    When the user clicks on each category without submenu
    Then the related category pages should be opened

    Examples:
      | userType  |
      | guest     |
      | LOGGED_IN |

  Scenario Outline: Subcategory links redirect correctly - TC005[ZYD19-5]
    Given the user is "<userType>"
    When the user hovers over a category with submenu
    And clicks the first subcategory link
    Then a valid product listing or landing page should be opened

    Examples:
      | userType  |
      | guest     |
      | LOGGED_IN |

  Scenario Outline: Category link should not redirect to wrong page - TC008[ZYD19-5]
    Given the user is "<userType>"
    When the user hovers over a category with submenu
    And clicks any subcategory link
    Then the category page should not be a wrong, empty or 404 page

    Examples:
      | userType  |
      | guest     |
      | LOGGED_IN |

  Scenario Outline: Dropdown menu opens within acceptable time on hover - TC006[ZYD19-5]
    Given the user is "<userType>"
    When the user hovers over each main category with submenu
    Then the dropdown menu should appear within 1 second

    Examples:
      | userType  |
      | guest     |
      | LOGGED_IN |





