package com.example.spreeCommerce.interfaces;

import com.microsoft.playwright.Page;

public interface BrowserManager {
    Page initBrowser(String url, String channel);
}
