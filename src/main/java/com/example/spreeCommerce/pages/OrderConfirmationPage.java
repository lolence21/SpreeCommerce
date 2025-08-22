package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iOrderConfirmationPage;
import com.microsoft.playwright.Page;

public class OrderConfirmationPage extends BasePage implements iOrderConfirmationPage {
    private final String ORDER_NUMBER = "p[class='text-sm mb-1 mt-3']";
    private final String SUCCESS_MESSAGE = ORDER_NUMBER + " + h4";
    private final String ORDER_CONFIRMATION = SUCCESS_MESSAGE + " + div h5";
    private final String CARD_NUMBER = "p[class='mb-1']";
    private final String CARD_EXPIRATION_DATE = CARD_NUMBER + "+ p";
    private final String PAYMENT_STATUS = "span[class='badge-paid']";
    private final String EMAIL = "p[class='mb-4']";
    private final String ADDRESS = "p[class='mb-4'] + div div:first-child p";
    private final String BILLING_ADDRESS = "p[class='mb-4'] + div div:first-child + div p";
    private final String ITEM_NAME = "#checkout_line_items p:first-child";
    private final String COLOR_AND_SIZE = "#checkout_line_items p:nth-of-type(2)";
    private final String ITEMS_PRICE = "span:has-text('Subtotal:') + span";
    private final String SHIPPING_PRICE = "span:has-text('Shipping:') + span";
    private final String TOTAL_PRICE = "span:has-text('Total') + div span:nth-of-type(2)";


    public OrderConfirmationPage(Page page) {
        super(page);
    }

    @Override
    public String getOrderNumber() {
        waitForElement(ORDER_NUMBER);
        return getElementText(ORDER_NUMBER);
    }

    @Override
    public String getSuccessMessage() {
        return getElementText(SUCCESS_MESSAGE);
    }

    @Override
    public String getOrderConfirmation() {
        return getElementText(ORDER_CONFIRMATION);
    }

    @Override
    public String getCardNumber() {
        return getElementText(CARD_NUMBER);
    }

    @Override
    public String getCardExpirationDate() {
        return getElementText(CARD_EXPIRATION_DATE);
    }

    @Override
    public String getPaymentStatus() {
        return getElementText(PAYMENT_STATUS);
    }

    @Override
    public String getEmail() {
        return getElementText(EMAIL) ;
    }

    @Override
    public String getAddress() {
        return getElementText(ADDRESS);
    }

    @Override
    public String getBillingAddress() {
        return getElementText(BILLING_ADDRESS);
    }

    @Override
    public String getItemName() {
        return getElementText(ITEM_NAME);
    }

    @Override
    public String getItemColorAndSize() {
        return getElementText(COLOR_AND_SIZE);
    }

    @Override
    public String getItemsPrice() {
        return getElementText(ITEMS_PRICE);
    }

    @Override
    public String getShippingPrice() {
        return getElementText(SHIPPING_PRICE);
    }

    @Override
    public String getTotalPrice() {
        return getElementText(TOTAL_PRICE);
    }
}
