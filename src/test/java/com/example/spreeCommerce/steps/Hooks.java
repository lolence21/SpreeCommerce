package com.example.spreeCommerce.steps;

import com.aventstack.extentreports.*;
import com.example.spreeCommerce.pages.BasePage;
import com.example.spreeCommerce.utils.ConfigReader;
import com.example.spreeCommerce.utils.ExtentManager;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks extends BasePage {
    ConfigReader configReader = new ConfigReader();

    @Before
    public void setUp(Scenario scenario) {
        ExtentManager.initReport();
        ExtentManager.createTest(scenario.getName());

        playwright = Playwright.create();
        page = initBrowser(configReader.readConfig("url"), configReader.readConfig("browser"));
        ExtentManager.getTest().log(Status.INFO, "Browser launched");
    }


    @AfterStep
    public void captureScreenshot(Scenario scenario) {
        try {
            String reportFolder = ExtentManager.getReportFolderPath();
            // Create screenshots directory if it doesn't exist
            Path screenshotDir = Paths.get(reportFolder, "screenshots");
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }
            String status = scenario.isFailed() ? "FAILED" : "PASSED";
            String screenshotName = status + "_" + System.currentTimeMillis() + ".png";
            Path screenshotPath = screenshotDir.resolve(screenshotName);
            page.screenshot(new Page.ScreenshotOptions().setPath(screenshotPath).setFullPage(false));

        } catch (Exception e) {
            ExtentManager.getTest().warning("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (page != null) closePage();
        if (browser != null) closeBrowser();
        if (playwright != null) closePlaywright();
        ExtentManager.getTest().log(Status.INFO, "Browser closed");
        ExtentManager.flushReport();
    }
}
