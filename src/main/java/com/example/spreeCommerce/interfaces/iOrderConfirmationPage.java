package com.example.spreeCommerce.interfaces;

public interface iOrderConfirmationPage {
    String getOrderNumber();
    String getSuccessMessage();
    String getOrderConfirmation();
    String getCardNumber();
    String getCardExpirationDate();
    String getPaymentStatus();
    String getEmail();
    String getAddress();
    String getBillingAddress();
    String getItemName();
    String getItemColorAndSize();
    String getItemsPrice();
    String getShippingPrice();
    String getTotalPrice();

}
