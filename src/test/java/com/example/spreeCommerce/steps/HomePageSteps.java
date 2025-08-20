package com.example.spreeCommerce.steps;
import com.aventstack.extentreports.Status;
import com.example.spreeCommerce.pages.BasePage;
import com.example.spreeCommerce.pages.HomePage;
import com.example.spreeCommerce.utils.ExtentManager;
import io.cucumber.java.en.Given;
import org.testng.Assert;
import org.testng.annotations.*;

public class HomePageSteps extends BasePage {

    HomePage homePage;

    public HomePageSteps() {
        homePage = new HomePage(page);
    }

    @Given("get title")
    public void homePageTitleTest(){
        try {
            String getTitle = homePage.getHomePageTitle();
            ExtentManager.getTest().log(Status.PASS, "title is " + getTitle);
        } catch (Exception e) {
            ExtentManager.getTest().log(Status.FAIL, "Get title failed");
            Assert.fail("Get title failed");
        }
    }

    @Test
    public void homePageUrlTest(){
        String actualUrl = homePage.getHomePageURL();
        Assert.assertEquals(actualUrl, "https://demo.spreecommerce.org/");
    }

    @Test
    public void searchTest(){
        String productName = "Checkered Shirt";
        homePage.clickSearch();
        homePage.enterSearchProduct(productName);
        String actualSearchHeader = homePage.getSearchResultText(productName);
        Assert.assertEquals(actualSearchHeader, "Checkered Shirt");
    }
}
