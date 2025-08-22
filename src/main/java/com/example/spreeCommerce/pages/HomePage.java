package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iHomePage;
import com.example.spreeCommerce.utils.ConfigReader;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage implements iHomePage{

    private final String USER_ICON = "div.hidden.lg\\:flex > ";
    private final String FLASH_MESSAGE = "#flashes p[class*='flash-message']";
    private final String SEARCH_BUTTON = "button#open-search span";
    private final String SEARCH_INPUT = "form[action='/search'] input[type='text']";
    private final String PRODUCT_DETAIL = "#search-suggestions ";

    public HomePage(Page page){
        super(page);
    }

    @Override
    public String navigateToSite(){
        navigateUrl(ConfigReader.readConfig("url"));
        return getUrl();
    }
    @Override
    public void clickUserIcon() {
        String loginIcon = USER_ICON.concat("button[data-action*='account']");
        String accountIcon = USER_ICON.concat("a[href='/account']");
        String userIcon = isElementVisible(loginIcon)
                ? loginIcon
                : accountIcon;
        waitForElement(userIcon, 3);
        clickElement(userIcon);
    }

    @Override
    public String getFlashMessage() {
        return getElementText(FLASH_MESSAGE).trim();
    }


    @Override
    public void clickSearch() {
        waitForElement(SEARCH_BUTTON, 3);
        clickElement(SEARCH_BUTTON);
    }

    @Override
    public void enterSearchProduct(String productName) {
        fillElement(SEARCH_INPUT, productName);
    }

    @Override
    public String getSearchProduct(String searchProduct) {
        String product =  PRODUCT_DETAIL.concat("a[href*='" + searchProduct.toLowerCase() + "']");
        return getElementLocator(product).textContent().trim();
    }

    @Override
    public void clickProduct(String productName) {
        String product =  PRODUCT_DETAIL.concat("a[href*='" + productName.toLowerCase() + "']");
        waitForElement(product, 3);
        clickElement(product);
    }
}
