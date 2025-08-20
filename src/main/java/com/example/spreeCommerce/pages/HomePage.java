package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iHomePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage implements iHomePage {
    private final Page page;

    private final String searchButton = "button#open-search span";
    private final String searchInput = "form[action='/search'] input[type='text']";
    private final String searchPageHeader = "div[id*='content'] h3";

    public HomePage(Page page){
        this.page = page;
    }

    @Override
    public String getHomePageTitle(){
        String title = page.title();
        System.out.println("title is " + title);
        return title;
    }

    public String getHomePageURL(){
        String url = page.url();
        System.out.println("url is " + url);
        return url;
    }

    @Override
    public void clickSearch() {
        Locator locSearch = page.locator(searchButton);
        locSearch.waitFor();
        locSearch.click();
    }

    @Override
    public void enterSearchProduct(String productName) {
        page.fill(searchInput, productName);
    }

    @Override
    public String getSearchResultText(String productName) {
        Locator itemName = page.locator(searchPageHeader).getByText(productName);
        return itemName.textContent().trim();
    }

}
