package com.example.spreeCommerce.steps;

import com.example.spreeCommerce.pages.CheckOutPage;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.util.Map;

public class CheckOutSteps extends BaseSteps {

    private final CheckOutPage checkOutPage;
    private final Page page = Hooks.getPage();

    public CheckOutSteps() {
        checkOutPage  = new CheckOutPage(page);
    }

    @And("add a shipping address")
    public void addShippingAddress(DataTable dataTable) {
        String message = "add a shipping address";
        try{
            String getAddressText = checkOutPage.getAddressSectionText();
            assertEqualsAndLog(getAddressText, "Shipping Address");

            Map<String, String> addressDetails = dataTable.asMaps(String.class, String.class).getFirst();
            checkOutPage.enterShippingAddress(addressDetails);
            checkOutPage.clickSaveAndContinue();
            Hooks.testContext.putAll(addressDetails);

            String shippingAddress = checkOutPage.getShippingAddress();
            for(Map.Entry<String, String> entry : addressDetails.entrySet()){
                assertTrueAndLog(shippingAddress.contains(entry.getValue()), "Shipping Address is correct", "Shipping Address is not correct");
            }

            passLogger(message);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }

    @And("select a shipping method - {string}")
    public void selectShippingMethod(String method) {
        String message = "select a shipping method";
        try{
            String getDeliveryText = checkOutPage.getDeliverySectionText();
            assertEqualsAndLog(getDeliveryText, "Delivery method from Shop location");

            checkOutPage.selectShippingMethod(method);
            Hooks.testContext.set("DeliveryMethod", method);
            checkOutPage.clickSaveAndContinue();

            passLogger(message);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }

    @And("select a payment method {string} and complete the order")
    public void selectPaymentMethodAndComplete(String method) {
        String message = "select a payment method and complete the order";
        try{
            String getDeliveryText = checkOutPage.getDeliveryMethod();
            String deliveryMethod = Hooks.testContext.get("DeliveryMethod", String.class);
            assertTrueAndLog(getDeliveryText.contains(deliveryMethod), "Delivery method is correct","Delivery method is incorrect");
            String getPaymentText = checkOutPage.getPaymentSectionText();
            assertEqualsAndLog(getPaymentText, "Payment");

            String shippingPrice = checkOutPage.getShippingPrice(method);
            Hooks.testContext.set("ShippingPrice", shippingPrice);

            checkOutPage.selectPaymentOption(method);
            Map<String, String> stripeDetails = checkOutPage.enterStripeDetails();
            Hooks.testContext.putAll(stripeDetails);
            checkOutPage.clickPayNow();

            passLogger(message);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }
}
