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

  @tulay
    Scenario: Verify that the user is able to increase the item’s quantity TC03-[ZYD19-9]:
    When The user clicks any product
    Then The user clicks "Kabuklu" product
    When The user increases the product quantity by clicking the + button
    When The user clicks the add to cart icon
    When The user views the shopping cart
   # Then The user verifies that the product quantity has increased productName "Kabuklu"  expectedQty 2