@wip
Feature: Search functionality
  As a user
  I want to search for products
  So that I can find relevant items easily

  Background:
    Given the user is on the home page


  Scenario Outline: Search for an existing product using Search button
    Given the user is "<userType>"
    When the user enters "<productName>" into the search bar
    And the user clicks the Search button
    Then search results should be displayed
    And at least one related product should be listed

    Examples:
      | userType  | productName |
      | guest     | bal         |
      | guest     | çekirdek    |
      | LOGGED_IN | kahve       |
      | LOGGED_IN | çay         |


  Scenario Outline: Navigate to product detail page from search results
    Given the user is "<userType>"
    When the user enters "<productName>" into the search bar
    And the user clicks the Search button
    And the user clicks on a product from the search results
    Then the product detail page should be displayed
    And the displayed product should match the selected product

    Examples:
      | userType  | productName |
      | guest     | bal         |
      | LOGGED_IN | kahve       |


  Scenario Outline: Display product not found message for non-existing product
    Given the user is "<userType>"
    When the user enters "<productName>" into the search bar
    And the user clicks the Search button
    Then no products should be listed
    And the "product not found" message should be displayed

    Examples:
      | userType  | productName |
      | guest     | lamba       |
      | LOGGED_IN | lamba       |


  Scenario Outline: Search for a product using Enter key
    Given the user is "<userType>"
    When the user enters "<productName>" into the search bar
    And the user presses the Enter key
    Then search results should be displayed
    And at least one related product should be listed

    Examples:
      | userType  | productName |
      | guest     | bal         |
      | LOGGED_IN | çay         |


  Scenario Outline: Search using predictive dropdown
    Given the user is "<userType>"
    When the user enters "<productName>" into the search bar
    Then predictive search results should be displayed in the dropdown
    When the user selects a product from the dropdown
    Then the product detail page should be displayed
    And the displayed product should match the selected dropdown product

    Examples:
      | userType  | productName |
      | guest     | bal         |
      | LOGGED_IN | kahve       |



