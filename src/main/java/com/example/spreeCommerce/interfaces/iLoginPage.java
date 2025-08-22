package com.example.spreeCommerce.interfaces;

public interface iLoginPage {
    String getLoginPageTitle();
    void enterEmail(String email);
    void enterPassword(String password);
    void enterPasswordConfirmation(String confirmPassword);
    void clickSignup();
    void clickLoginButton();
    void clickSignupButton();
}
