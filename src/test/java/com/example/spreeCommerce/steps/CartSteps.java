package com.example.spreeCommerce.steps;

import com.example.spreeCommerce.pages.CartPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSteps extends BaseSteps {

    private final CartPage cartPage;
    private final Page page = Hooks.getPage();

    public CartSteps() {
        cartPage  = new CartPage(page);
    }

    @Then("the user verifies the product details in the cart")
    public void userVerifiesTheProductDetailsInTheCart() {
        String message = "user verifies the product details in the cart";
        try{
            String getCartTitle = cartPage.getCartPageTitle();
            assertEqualsAndLog(getCartTitle, "Cart");

            String productName = Hooks.testContext.get("ProductName", String.class);
            boolean isProductNameExists = cartPage.isProductNameExists(productName);
            assertTrueAndLog(isProductNameExists, "Product Name found","Product Name not found");

            String quantity = Hooks.testContext.get("Quantity", String.class);
            boolean isProductQuantityExists = cartPage.isProductQuantityExists(quantity);
            assertTrueAndLog(isProductQuantityExists, "Product Quantity found",  "Product Quantity not found");

            String price = Hooks.testContext.get("Price", String.class);
            boolean isProductPriceExists = cartPage.isProductPriceExists(price);
            assertTrueAndLog(isProductPriceExists, "Product Price found","Product Price not found");
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }

    @When("the user proceed to checkout")
    public void userProceedToCheckout() {
        String message = "user proceed to checkout";
        try{
            cartPage.clickCheckoutButton();
            passLogger(message);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }
}
