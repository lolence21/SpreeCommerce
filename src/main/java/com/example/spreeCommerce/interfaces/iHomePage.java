package com.example.spreeCommerce.interfaces;

public interface iHomePage {

    String getHomePageTitle();
    String getHomePageURL();
    void clickSearch();
    void enterSearchProduct(String searchProduct);
    String getSearchResultText(String productName);
    void clickUserIcon();
    boolean verifySignedUpSuccess();
}
