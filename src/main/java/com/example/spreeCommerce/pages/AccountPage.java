package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iAccountPage;
import com.microsoft.playwright.Page;

public class AccountPage extends BasePage implements iAccountPage {

    private final String ACCOUNT_PAGE_TITLE = "div[class*='lg:col-span-3'] h1";
    private final String LOGOUT_BUTTON = "form[action*='sign_out'] button";

    public AccountPage(Page page){
        super(page);
    }

    @Override
    public String getAccountPageTitle() {
        waitForElement(ACCOUNT_PAGE_TITLE, 3);
        return getElementText(ACCOUNT_PAGE_TITLE);
    }

    @Override
    public void clickLogoutButton() {
        clickElement(LOGOUT_BUTTON);
    }
}
