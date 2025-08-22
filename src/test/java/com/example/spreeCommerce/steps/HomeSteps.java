package com.example.spreeCommerce.steps;
import com.aventstack.extentreports.Status;
import com.example.spreeCommerce.pages.HomePage;
import com.example.spreeCommerce.utils.ConfigReader;
import com.example.spreeCommerce.utils.ExtentManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HomeSteps extends BaseSteps {

    private final HomePage homePage;

    public HomeSteps() {
        homePage = new HomePage(Hooks.getPage());
    }

    @Given("the user navigates to the Spree Commerce demo store")
    public void theUserNavigatesToTheSpreeCommerceDemoStore(){
        String message = "navigate to the Spree Commerce demo store";
        try {
            String actualUrl = homePage.navigateToSite();
            String expectedUrl = ConfigReader.readConfig("url");
            Assert.assertEquals(actualUrl, expectedUrl, "Mismatch: " + message);

            ExtentManager.getTest().log(Status.PASS, message + ": " + actualUrl);
            logger.info("{} - {}", message, Status.PASS);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " FAILED - " + e.getMessage());
        }
    }

    @When("the user search for a product {string} and open a product detail page")
    public void theUserSearchForAProduct(String productName){
        String message = "user browse products and open a product detail page";
        try {
            homePage.clickSearch();
            homePage.enterSearchProduct(productName);
            String actualProductText = homePage.getSearchProduct(productName);
            assertTrueAndLog(actualProductText.contains(productName), "Product Name found", "Product Name not found");
            homePage.clickProduct(productName);
            passLogger(message);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(message + " " + e);
        }
    }
}
