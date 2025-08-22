package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iCartPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage extends BasePage implements iCartPage {

    private final String PRODUCT_NAME = "#slideover-cart > div > span[class*='text']";
    private final String CART_ITEMS = "#line-items div[class='ml-3 w-full'] ";
    private final String CHECKOUT_BUTTON = "a[href*='checkout']";

    private static int productIndex = 0;

    public CartPage(Page page){
        super(page);
    }

    @Override
    public String getCartPageTitle() {
        waitForElement(PRODUCT_NAME, 5);
        return getElementText(PRODUCT_NAME).trim();
    }

    @Override
    public boolean isProductNameExists(String productName) {
        Locator cartItems= page.locator(CART_ITEMS.concat("a"));
        boolean isName = false;
        for (int i = 0; i < cartItems.count(); i++) {
            if (productName.equalsIgnoreCase(cartItems.nth(i).textContent().trim())) {
                isName = true;
                productIndex = i;
                break;
            }
        }
        return isName;
    }

    @Override
    public boolean isProductQuantityExists(String productQuantity) {
        Locator cartItems= page.locator(CART_ITEMS.concat("input[name*='line_item']"));
        return productQuantity.equalsIgnoreCase(cartItems.nth(productIndex).inputValue());
    }

    @Override
    public boolean isProductPriceExists(String price) {
        Locator cartItems= page.locator(CART_ITEMS.concat("div[class*='mb-2 text-sm'] span"));
        return price.equalsIgnoreCase(cartItems.nth(productIndex).textContent().trim());
    }

    @Override
    public void clickCheckoutButton() {
        clickElement(CHECKOUT_BUTTON);
    }
}
