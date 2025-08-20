package com.example.spreeCommerce.interfaces;

public interface iProductPage {
    void verifyProductPage(String productName);
    void chooseColor(String color);
    void chooseSize(String size);
    void setQuantity(int quantity);
    void clickAddToCart();
}
