package com.example.spreeCommerce.interfaces;

public interface iCartPage {
    boolean verifyCartPage();
    boolean verifyProductName(String name);
    boolean verifyProductQuantity(int quantity);
    boolean verifyProductPrice(double price);
    void clickCheckoutButton();
}
