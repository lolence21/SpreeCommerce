package com.example.spreeCommerce.interfaces;

public interface iCartPage {
    String getCartPageTitle();
    boolean isProductNameExists(String productName);
    boolean isProductQuantityExists(String productQuantity);
    boolean isProductPriceExists(String price);
    void clickCheckoutButton();
}
