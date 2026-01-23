
Feature: Shopping Cart Functionality

  Background:
    Given The user is on the main login page
    When The user enters valid email
    And The user enters valid password
    Then The user clicks to login button


    Scenario: Verify that the shopping cart icon is clickable or not TC01-[ZYD19-9]
    When The user clicks the shopping cart icon
    Then The user verifies that shopping page is open


    Scenario: Verify that the user is able to add items into the cart or not TC02-[ZYD19-9]
    When The user clicks "Kabuklu" product
    When The user clicks the add to cart icon
    When The user views the shopping cart
    Then The user verifies the product "Kabuklu" is in the shopping cart


    Scenario: Verify that the user can increase and decrease the product quantity on the products page TC03-[ZYD19-9]: TC05-[ZYD19-9]
    When The user clicks any product
    Then The user clicks "Kabuklu" product
    When The user increases the product quantity by clicking the + button
    When The user decreases the product quantity by clicking the – button
    When The user clicks the add to cart icon
    When The user views the shopping cart

    Scenario: Verify that the user can increase and decrease the product quantity on the shopping cart page
    When The user clicks any product
    Then The user clicks "Kabuklu" product
    When The user increases the product quantity by clicking the + button in the shopping cart page
    When The user clicks the add to cart icon
    When The user views the shopping cart
    When The quantity on the product page should be the same as the quantity in the cart

  @tulay
    Scenario: Verify that when hovering over the cart icon, a quick view of the cart contents is displayed TC04-[ZYD19-9]
    When The user hovers over the cart icon and verifies that the cart contents are displayed quickly
    

    Scenario: When more than 10 units of the same product are added to the cart, the system should warn the user TC06-[ZYD19-9]
    When The user clicks any product
    When The user clicks "Kabuklu" product
    Then The user enters a quantity of 11 in the product quantity field
    When The user clicks the add to cart icon
    When The user views the shopping cart

