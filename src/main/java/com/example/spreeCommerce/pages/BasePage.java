package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.BrowserManager;
import com.example.spreeCommerce.utils.ConfigReader;
import com.microsoft.playwright.*;

public class BasePage implements BrowserManager {
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext browserContext;
    public static Page page;
    ConfigReader configReader = new ConfigReader();

    @Override
    public Page initBrowser(String url, String channel) {
        try {
            playwright = Playwright.create();

            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setChannel(channel)
                    .setHeadless(Boolean.parseBoolean(configReader.readConfig("headless")));

            switch (channel.toLowerCase()) {
                case "firefox" -> browser = playwright.firefox().launch(launchOptions);
                case "chrome" -> browser = playwright.chromium().launch(launchOptions);
                default -> throw new IllegalArgumentException("Unsupported browser: " + channel);
            }

            browserContext = browser.newContext();
            Page page = browserContext.newPage();
            page.setViewportSize(1920, 1080);
            page.navigate(url);
            return page;

        } catch (Exception e) {
            System.err.println("Browser Initialization Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void clickElement(String element) {

    }

    @Override
    public void fillElement(String element, String value) {

    }

    public void closePage() {
        page.close();
    }
    public void closePlaywright() {
        playwright.close();
    }
    public void closeBrowser() {
        browser.close();
    }

}
