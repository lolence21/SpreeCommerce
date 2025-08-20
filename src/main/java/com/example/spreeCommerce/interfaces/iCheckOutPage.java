package com.example.spreeCommerce.interfaces;

import java.util.List;

public interface iCheckOutPage {

    boolean verifyAddressSection();
    void enterShippingAddress(List<String> address);

    boolean verifyDeliverySection();
    void selectShippingMethod(String method);

    boolean verifyPaymentSection();
    void selectPaymentOption(String option);
    void enterStripeDetails(List<String> stripeDetails);

    void clickSaveAndContinue();

    void clickPayNow();
}
