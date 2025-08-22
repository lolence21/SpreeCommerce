package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iProductPage;
import com.microsoft.playwright.Page;

public class ProductPage extends BasePage implements iProductPage {

    private final String PRODUCT_NAME = "#product-details-page div[class*='block'] > h1";
    private final String CHOOSE_SIZE_BUTTON = "button[data-action*='dropdown']";
    private final String QUANTITY_FIELD = "#quantity";
    private final String ADD_TO_CART_BUTTON = "div:not([class*='hidden']) > button[data-product-form-target='submit']";
    private final String PRICE_TEXT = "#product-details-page > div:not([class*='hidden']) span + p";
    private String price;

    public ProductPage(Page page){
        super(page);
    }

    @Override
    public String getProductName() {
        waitForElement(PRODUCT_NAME, 5);
        return getElementLocator(PRODUCT_NAME).textContent().trim();
    }

    @Override
    public void chooseColor(String color) {
        clickElement("input[type='radio'][value='" + color.toLowerCase() + "'] + div");
    }

    @Override
    public void chooseSize(String size) {
        clickElement(CHOOSE_SIZE_BUTTON);
        clickElement("input[type='radio'][value='" + size.toLowerCase() + "'] + label");
    }

    @Override
    public void setQuantity(String quantity) {
        fillElement(QUANTITY_FIELD, quantity);
    }

    @Override
    public void clickAddToCart() {
        clickElement(ADD_TO_CART_BUTTON);
    }

    public void retrievePrice(){
        price = getElementText(PRICE_TEXT);
    }

    public String getPrice(){
        return price;
    }
}
