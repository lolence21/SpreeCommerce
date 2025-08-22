package com.example.spreeCommerce.interfaces;

import java.util.Map;

public interface iCheckOutPage {

    String getAddressSectionText();
    void enterShippingAddress(Map<String, String> address);
    String getShippingAddress();

    String getDeliverySectionText();
    void selectShippingMethod(String method);
    String getShippingPrice(String method);
    String getDeliveryMethod();

    String getPaymentSectionText();
    void selectPaymentOption(String option);
    Map<String, String> enterStripeDetails();

    void clickSaveAndContinue();

    void clickPayNow();
}
