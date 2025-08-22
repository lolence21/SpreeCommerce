package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iLoginPage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage implements iLoginPage {

    private final String LOGIN_PAGE_TITLE = "#login > div > h2";
    private final String SIGNUP_LINK = "a[href*='sign_up']";
    private final String EMAIL_FIELD = "#user_email";
    private final String PASSWORD_FIELD = "#user_password";
    private final String PASSWORD_CONFIRMATION_FIELD = "#user_password_confirmation";
    private final String LOGIN_BUTTON = "#login-button";
    private final String SIGNUP_BUTTON = "#new_user > div.actions > input";

    public LoginPage(Page page){
        super(page);
    }

    @Override
    public String getLoginPageTitle() {
        waitForElement(LOGIN_PAGE_TITLE, 3);
        return getElementText(LOGIN_PAGE_TITLE);
    }

    @Override
    public void enterEmail(String email) {
        fillElement(EMAIL_FIELD, email);
    }

    @Override
    public void enterPassword(String password) {
        fillElement(PASSWORD_FIELD, password);
    }

    @Override
    public void enterPasswordConfirmation(String confirmPassword) {
        fillElement(PASSWORD_CONFIRMATION_FIELD, confirmPassword);
    }

    @Override
    public void clickSignup() {
        clickElement(SIGNUP_LINK);
    }

    @Override
    public void clickLoginButton() {
        clickElement(LOGIN_BUTTON);
    }

    @Override
    public void clickSignupButton() {
        clickElement(SIGNUP_BUTTON);
    }
}
