package com.example.spreeCommerce.interfaces;

public interface iHomePage {

    String navigateToSite();
    void clickSearch();
    void enterSearchProduct(String searchProduct);
    void clickProduct(String productName);
    String getSearchProduct(String searchProduct);
    void clickUserIcon();
    String getFlashMessage();
}
