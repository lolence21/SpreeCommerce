package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iBasePage;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage implements iBasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    @Override
    public void navigateUrl(String url) {
        page.navigate(url);
    }

    @Override
    public String getUrl() {
        return page.url();
    }

    @Override
    public boolean isElementVisible(String locator) {
        return page.isVisible(locator);
    }

    @Override
    public void waitForElement(String locator) {
        page.locator(locator).waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
    }

    @Override
    public void waitForElement(String locator, int seconds) {
        page.waitForTimeout(seconds*1000);
        waitForElement(locator);
    }

    @Override
    public String getElementText(String locator) {
        return page.textContent(locator)
                .replaceAll("[\\r\\n]+", "")
                .replaceAll("\\s{2,}", " ")
                .trim();
    }

    @Override
    public void clickElement(String locator) {
        getElementLocator(locator).scrollIntoViewIfNeeded();
        page.click(locator);
    }

    @Override
    public void fillElement(String locator, String value) {
        getElementLocator(locator).scrollIntoViewIfNeeded();
        page.fill(locator, String.valueOf(value));
        if(!getElementText(locator).equalsIgnoreCase(value)) {
            fillElementForNumericInput(locator, value);
        }
    }

    @Override
    public void fillElementForNumericInput(String locator, String value) {
        page.locator(locator).click();
        page.locator(locator).press("Control+A");
        page.locator(locator).press("Delete");
        page.locator(locator).fill(value);
    }

    @Override
    public void selectOption(String locator, String value) {
        getElementLocator(locator).scrollIntoViewIfNeeded();
        page.locator(locator).selectOption(new SelectOption().setLabel(value));
    }

    @Override
    public Locator getElementLocator(String locator) {
        return page.locator(locator);
    }

    @Override
    public FrameLocator getFrame(String frameLocator) {
        return page.frameLocator(frameLocator);
    }

    @Override
    public boolean isElementVisibleOnFrame(String frameLocator, String locator) {
        return getFrame(frameLocator).locator(locator).isVisible();
    }

    @Override
    public void clickElementOnFrame(String frameLocator, String locator) {
        getFrame(frameLocator).locator(locator).scrollIntoViewIfNeeded();
        getFrame(frameLocator).locator(locator).click();
    }

    @Override
    public void fillElementOnFrame(String frameLocator, String locator, String value) {
        getFrame(frameLocator).locator(locator).scrollIntoViewIfNeeded();
        getFrame(frameLocator).locator(locator).fill(value);
        if(!getElementTextOnFrame(frameLocator, locator).equalsIgnoreCase(value)){
            fillElementForNumericInputOnFrame(frameLocator, locator, value);
        }
    }

    @Override
    public void fillElementForNumericInputOnFrame(String frameLocator, String locator, String value) {
        clickElementOnFrame(frameLocator, locator);
        getFrame(frameLocator).locator(locator).press("Control+A");
        getFrame(frameLocator).locator(locator).press("Delete");
        getFrame(frameLocator).locator(locator).fill(value);
    }

    @Override
    public String getElementTextOnFrame(String frameLocator, String locator) {
        getFrame(frameLocator).locator(locator).scrollIntoViewIfNeeded();
        return getFrame(frameLocator).locator(locator).textContent();
    }
}
