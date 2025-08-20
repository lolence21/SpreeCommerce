package com.example.spreeCommerce.steps;
import com.aventstack.extentreports.Status;
import com.example.spreeCommerce.pages.BasePage;
import com.example.spreeCommerce.pages.HomePage;
import com.example.spreeCommerce.utils.ExtentManager;
import io.cucumber.java.en.Given;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import org.slf4j.Logger;

public class HomePageSteps extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePageSteps.class);
    HomePage homePage;

    public HomePageSteps() {
        homePage = new HomePage(page);
    }

    @Given("get title")
    public void homePageTitleTest(){
        try {
            String getTitle = homePage.getHomePageTitle();
            ExtentManager.getTest().log(Status.PASS, "title is " + getTitle);
            logger.info("{} : title is {}", Status.PASS, getTitle);
        } catch (Exception e) {
            ExtentManager.getTest().log(Status.FAIL, "Get title failed");
            logger.error("Get title failed");
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
