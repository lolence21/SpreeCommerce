package com.example.spreeCommerce.steps;

import com.aventstack.extentreports.*;
import com.example.spreeCommerce.utils.ConfigReader;
import com.example.spreeCommerce.utils.ExtentManager;
import com.example.spreeCommerce.utils.TestContext;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.Assert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    private static Playwright playwright;
    private static Browser browser;
    private static Page page;

    public static TestContext testContext;

    public static Page getPage() {
        return page;
    }

    @Before
    public void setUp(Scenario scenario) {
        ExtentManager.initReport();
        ExtentManager.createTest(scenario.getName());
        testContext = new TestContext();

        try {
            playwright = Playwright.create();
            String channel = ConfigReader.readConfig("browser");
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setChannel(channel)
                    .setHeadless(Boolean.parseBoolean(ConfigReader.readConfig("headless")));

            switch (channel.toLowerCase()) {
                case "firefox" -> browser = playwright.firefox().launch(launchOptions);
                case "chrome" -> browser = playwright.chromium().launch(launchOptions);
                default -> throw new IllegalArgumentException("Unsupported browser: " + channel);
            }

            BrowserContext browserContext = browser.newContext();
            page = browserContext.newPage();
            page.setViewportSize(1920, 1080);
            ExtentManager.getTest().log(Status.INFO, "Browser launched");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Browser Initialization Error: " + e.getMessage());
            Assert.fail("Browser Initialization Error: " + e.getMessage());
        }
    }


    @AfterStep
    public void captureScreenshot(Scenario scenario) {
        try {
            String reportFolder = ExtentManager.getReportFolderPath();
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
        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
        ExtentManager.getTest().log(Status.INFO, "Browser closed");
        ExtentManager.flushReport();
    }
}
