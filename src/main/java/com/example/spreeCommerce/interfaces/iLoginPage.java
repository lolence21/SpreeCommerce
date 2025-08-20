package com.example.spreeCommerce.interfaces;

public interface iLoginPage {
    boolean verifyLoginPage();
    void enterEmail(String email);
    void enterPassword(String password);
    void enterPasswordConfirmation(String confirmPassword);
    void clickSignup();
    void clickLogin();
    void clickLoginButton();
    void clickSignupButton();
}
