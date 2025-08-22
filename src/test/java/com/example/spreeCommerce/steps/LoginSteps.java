package com.example.spreeCommerce.steps;

import com.example.spreeCommerce.pages.AccountPage;
import com.example.spreeCommerce.pages.HomePage;
import com.example.spreeCommerce.pages.LoginPage;
import com.example.spreeCommerce.utils.ConfigReader;
import com.example.spreeCommerce.utils.EmailGenerator;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;

public class LoginSteps extends BaseSteps {

    private final HomePage homePage;
    private final LoginPage loginPage;
    private final AccountPage accountPage;
    private final Page page = Hooks.getPage();

    public LoginSteps() {
        loginPage = new LoginPage(page);
        homePage = new HomePage(page);
        accountPage  = new AccountPage(page);
    }

    @And("the user sign up as a new user")
    public void userSignUpAsANewUser() {
        String message = "user sign up as a new user";
        try {
            homePage.clickUserIcon();
            loginPage.clickSignup();
            assertEqualsAndLog(loginPage.getLoginPageTitle(), "Sign Up");

            String email = EmailGenerator.generateUniqueEmail();
            Hooks.testContext.set("Email", email);
            String password = ConfigReader.readConfig("password");

            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.enterPasswordConfirmation(password);
            loginPage.clickSignupButton();

            String getSuccessText = homePage.getFlashMessage();
            assertEqualsAndLog(getSuccessText, "Welcome! You have signed up successfully.");
            homePage.clickUserIcon();
            String accountTitle = accountPage.getAccountPageTitle();
            assertEqualsAndLog(accountTitle, "My Account");
            accountPage.clickLogoutButton();

            passLogger(message);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " FAILED - " + e.getMessage());
        }
    }

    @And("the user log in with the registered user credentials")
    public void userLogInWithTheRegisteredUserCredentials() {
        String message = "user log in with the registered user credentials";
        try{
            homePage.clickUserIcon();
            assertEqualsAndLog(loginPage.getLoginPageTitle(), "Login");

            String email = Hooks.testContext.get("Email", String.class);
            String password = ConfigReader.readConfig("password");
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLoginButton();

            String getSuccessText = homePage.getFlashMessage();
            assertEqualsAndLog(getSuccessText, "Signed in successfully.");
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }
}
