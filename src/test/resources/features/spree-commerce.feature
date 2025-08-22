Feature: Spree Commerce Order Flow

  @spree-commerce-test
  Scenario Outline: Spree Commerce E2E UI Scenario for Product - <Product>
    Given the user navigates to the Spree Commerce demo store
    And the user sign up as a new user
    And the user log in with the registered user credentials
    When the user search for a product "<Product>" and open a product detail page
    And the user add the product to the cart
      | Title     | Color    | Size     | Quantity    |
      | <Product> | <Color>  | <Size>   | <Quantity>  |
    Then the user verifies the product details in the cart
    When the user proceed to checkout
    And add a shipping address
      | Country     | FirstName | LastName  | Street      | City   | Postal |
      | Philippines | Juan      | Dela Cruz | 32nd Street | Taguig | 1635   |
    And select a shipping method - "<Shipping>"
    And select a payment method "<Payment>" and complete the order
    Then the user verifies the order confirmation page with a success message and order number

  Examples:
    | Product   | Color | Size | Quantity | Shipping | Payment |
    | Dotted    | Pink  | L    | 2        | Premium  | Stripe  |
    | Checkered | Teal  | M    | 3        | Next Day | Stripe  |