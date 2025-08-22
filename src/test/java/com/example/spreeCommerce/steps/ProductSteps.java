package com.example.spreeCommerce.steps;

import com.example.spreeCommerce.pages.ProductPage;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.util.Map;

public class ProductSteps extends BaseSteps {

    private final ProductPage productPage;
    private final Page page = Hooks.getPage();

    public ProductSteps() {
        productPage  = new ProductPage(page);
    }

    @And("the user add the product to the cart")
    public void theUserAddTheProductToTheCart(DataTable dataTable) {
        String message = "user add the product to the cart";
        try{
            Map<String, String> productDetails = dataTable.asMaps(String.class, String.class).getFirst();
            String title = productDetails.get("Title");
            String color = productDetails.get("Color");
            String size = productDetails.get("Size");
            String quantity = productDetails.get("Quantity");

            //Storing Details for verification
            Hooks.testContext.putAll(productDetails);
            productPage.retrievePrice();
            Hooks.testContext.set("Price", productPage.getPrice());

            String getProductName = productPage.getProductName();
            assertTrueAndLog(getProductName.contains(title), "The product " + title + " does exist", "The product " + title + " does not exist");
            Hooks.testContext.set("ProductName", getProductName);

            productPage.chooseColor(color);
            productPage.chooseSize(size);
            productPage.setQuantity(quantity);
            productPage.clickAddToCart();

            passLogger(message);

        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }

}
