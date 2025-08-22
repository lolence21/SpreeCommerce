package com.example.spreeCommerce.interfaces;

public interface iProductPage {
    String getProductName();
    void chooseColor(String color);
    void chooseSize(String size);
    void setQuantity(String quantity);
    void clickAddToCart();
}
