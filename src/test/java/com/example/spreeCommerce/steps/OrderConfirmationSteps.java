package com.example.spreeCommerce.steps;

import com.aventstack.extentreports.Status;
import com.example.spreeCommerce.pages.OrderConfirmationPage;
import com.example.spreeCommerce.utils.ExtentManager;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.Then;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderConfirmationSteps extends BaseSteps {

    private final OrderConfirmationPage orderConfirmationPage;
    private final Page page = Hooks.getPage();

    public OrderConfirmationSteps() {
        orderConfirmationPage = new OrderConfirmationPage(page);
    }

    @Then("the user verifies the order confirmation page with a success message and order number")
    public void verifyOrderConfirmationPageDetails() {
        String message = "user log in with the registered user credentials";
        try{
            assertTrueAndLog(orderConfirmationPage.getOrderNumber().contains("Order R"), "Order number is displayed", "Order number is not displayed");
            String firstName = Hooks.testContext.get("FirstName",String.class);
            assertEqualsAndLog(orderConfirmationPage.getSuccessMessage(),"Thanks " + firstName + " for your order!");
            assertEqualsAndLog(orderConfirmationPage.getOrderConfirmation(),"Your order is confirmed!");

            String cardNumber = Hooks.testContext.get("CardNumber",String.class);
            cardNumber = cardNumber.substring(cardNumber.length() - 4);
            assertTrueAndLog(orderConfirmationPage.getCardNumber().contains(cardNumber), "Card number is correct", "Card number is incorrect");

            String expiryDate = Hooks.testContext.get("ExpiryDate",String.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
            YearMonth date = YearMonth.parse(expiryDate, formatter);
            formatter = DateTimeFormatter.ofPattern("M/yyyy");
            String formattedDate = date.format(formatter);
            assertTrueAndLog(orderConfirmationPage.getCardExpirationDate().contains(formattedDate), "Expiration Date is correct", "Expiration Date is incorrect");

            assertEqualsAndLog(orderConfirmationPage.getPaymentStatus(), "Paid");
            String email = Hooks.testContext.get("Email",String.class);
            assertEqualsAndLog(orderConfirmationPage.getEmail(), email);

            List<String> address = new ArrayList<>();
            address.add(Hooks.testContext.get("Country",String.class));
            address.add(Hooks.testContext.get("FirstName",String.class) + " " + Hooks.testContext.get("LastName",String.class));
            address.add(Hooks.testContext.get("Street",String.class));
            address.add(Hooks.testContext.get("City",String.class));
            address.add(Hooks.testContext.get("Postal",String.class));
            for(String details : address){
                assertTrueAndLog(orderConfirmationPage.getAddress().contains(details) , details + " is displayed", details + " is not displayed");
                assertTrueAndLog(orderConfirmationPage.getBillingAddress().contains(details) , details + " is displayed", details + " is not displayed");
            }

            String itemName = Hooks.testContext.get("ProductName",String.class);
            assertEqualsAndLog(orderConfirmationPage.getItemName(), itemName);
            String itemColor = Hooks.testContext.get("Color",String.class);
            assertTrueAndLog(orderConfirmationPage.getItemColorAndSize().contains(itemColor), "Item color is correct", "Item color is incorrect");
            String itemSize = Hooks.testContext.get("Size",String.class);
            assertTrueAndLog(orderConfirmationPage.getItemColorAndSize().contains(itemSize), "Item size is correct","Item size is incorrect");

            double price = priceStringtoDouble(Hooks.testContext.get("Price",String.class));
            int quantity = Integer.parseInt(Hooks.testContext.get("Quantity",String.class));
            double getItemsPrice = priceStringtoDouble(orderConfirmationPage.getItemsPrice());
            double productItemsPrice = price * quantity;
            assertEqualsAndLog(getItemsPrice, productItemsPrice);

            String shippingPrice = Hooks.testContext.get("ShippingPrice",String.class);
            double shipPrice = priceStringtoDouble(shippingPrice);
            if(shipPrice == 0){
                assertTrueAndLog(orderConfirmationPage.getShippingPrice().equalsIgnoreCase("Free"), "Shipping is free", "Shipping is not free");
            }else {
                double getShippingPrice = priceStringtoDouble(orderConfirmationPage.getShippingPrice());
                assertEqualsAndLog(getShippingPrice, shipPrice);
            }

            double totalPrice = productItemsPrice + shipPrice;
            double orderTotal = priceStringtoDouble(orderConfirmationPage.getTotalPrice());
            assertEqualsAndLog(orderTotal, totalPrice);

            ExtentManager.getTest().log(Status.PASS, message + ": " + "All verified in order confirmation page");
            logger.info("{} - {}", message, Status.PASS);

        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }
}
