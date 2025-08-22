package com.example.spreeCommerce.interfaces;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;

public interface iBasePage {
    void navigateUrl(String url);
    String getUrl();

    boolean isElementVisible(String locator);
    void waitForElement(String locator);
    void waitForElement(String locator, int seconds);

    String getElementText(String locator);

    void clickElement(String locator);

    void fillElement(String locator, String value);
    void fillElementForNumericInput(String locator, String value);

    void selectOption(String locator, String value);

    Locator getElementLocator(String locator);

    FrameLocator getFrame(String frameLocator);
    boolean isElementVisibleOnFrame(String frameLocator, String locator);
    void clickElementOnFrame(String frameLocator, String locator);
    void fillElementOnFrame(String frameLocator, String locator, String value);
    void fillElementForNumericInputOnFrame(String frameLocator, String locator, String value);
    String getElementTextOnFrame(String frameLocator, String locator);
}
