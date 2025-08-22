package com.example.spreeCommerce.pages;

import com.example.spreeCommerce.interfaces.iCheckOutPage;
import com.microsoft.playwright.Page;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class CheckOutPage extends BasePage implements iCheckOutPage {

    private final String SHIPPING_LABEL = "#shipping h5";
    private final String COUNTRY_DROPDOWN = "#scountry select";
    private final String FIRST_NAME_INPUT = "#sfirstname input";
    private final String LAST_NAME_INPUT = "#slastname input";
    private final String STREET_INPUT = "#saddress1 input";
    private final String STREET_AUTOCOMPLETE = "#saddress1 + div[aria-label='suggestions'] li:first-child";
    private final String CITY_INPUT = "#scity input";
    private final String STATE_INPUT = "#sstate select";
    private final String POSTAL_INPUT = "#szipcode input";
    private final String SAVE_CONTINUE_PAY_BUTTON = "#checkout_content button";

    private final String DELIVERY_LABEL = "div[class='shipment'] h5";
    private final String DELIVERY_ADDRESS = "div[class='px-5 word-break']";
    private final String DELIVERY_STANDARD_RADIO = "label:has-text('Standard')";
    private final String DELIVERY_PREMIUM_RADIO = "label:has-text('Premium')";
    private final String DELIVERY_NEXT_DAY_RADIO = "label:has-text('Next Day')";

    private final String PAYMENT_LABEL = "#checkout_payment_methods h5";
    private final String DELIVERY_METHOD = "p[class='word-break']";
    private final String STRIPE_RADIO = "input[type='radio'][id*='id_25']";
    private final String CHECK_RADIO = "input[type='radio'][id*='id_24']";
    private final String STRIPE_FRAME = "iframe[title='Secure payment input frame']";
    private final String STRIPE_CARD_NUMBER_FIELD = "#Field-numberInput";
    private final String STRIPE_EXPIRATION_DATE_FIELD = "#Field-expiryInput";
    private final String STRIPE_SECURITY_CODE_FIELD = "#Field-cvcInput";
    private final String CARD_NUMBER= "#checkout-message li:first-child";
    private final String CARD_ERROR = "#Field-numberError";
    private final String EXPIRY_ERROR = "#Field-expiryError";

    public CheckOutPage(Page page) {
        super(page);
    }

    @Override
    public String getAddressSectionText() {
        waitForElement(SHIPPING_LABEL, 5);
        return getElementText(SHIPPING_LABEL);
    }

    @Override
    public void enterShippingAddress(Map<String, String> address) {
        selectOption(COUNTRY_DROPDOWN, address.get("Country"));
        fillElement(FIRST_NAME_INPUT, address.get("FirstName"));
        fillElement(LAST_NAME_INPUT, address.get("LastName"));
        fillElement(STREET_INPUT, address.get("Street"));
        waitForElement(STREET_AUTOCOMPLETE);
        clickElement(STREET_AUTOCOMPLETE);
        fillElement(CITY_INPUT, address.get("City"));
        if(isElementVisible(STATE_INPUT)) fillElement(STATE_INPUT, address.get("State"));
        fillElement(POSTAL_INPUT, address.get("Postal"));
    }

    @Override
    public String getShippingAddress() {
        waitForElement(DELIVERY_ADDRESS, 5);
        return getElementText(DELIVERY_ADDRESS);
    }

    @Override
    public String getDeliverySectionText() {
        return getElementText(DELIVERY_LABEL);
    }

    @Override
    public void selectShippingMethod(String method) {
        switch (method.toUpperCase()) {
            case "STANDARD" -> clickElement(DELIVERY_STANDARD_RADIO);
            case "PREMIUM" -> clickElement(DELIVERY_PREMIUM_RADIO);
            case "NEXT DAY" -> clickElement(DELIVERY_NEXT_DAY_RADIO);
            default -> throw new RuntimeException("Invalid input: " + method);
        }
    }

    @Override
    public String getShippingPrice(String method) {
        return getElementText(DELIVERY_METHOD + " strong");
    }

    @Override
    public String getDeliveryMethod() {
        waitForElement(DELIVERY_METHOD, 5);
        return getElementText(DELIVERY_METHOD);
    }

    @Override
    public String getPaymentSectionText() {
        return getElementText(PAYMENT_LABEL);
    }

    @Override
    public void selectPaymentOption(String option) {
        switch (option.toUpperCase()) {
            case "STRIPE" -> clickElement(STRIPE_RADIO);
            case "CHECK" -> clickElement(CHECK_RADIO);
            default -> throw new RuntimeException("Invalid input: " + option);
        }
    }

    @Override
    public Map<String, String> enterStripeDetails() {
        Map<String, String> details = new HashMap<>();
        //Insert Card Number
        waitForElement(CARD_NUMBER, 3);
        String cardNumber = getElementText(CARD_NUMBER)
                .replace("Card No.:", "");
        do {
            fillElementOnFrame(STRIPE_FRAME, STRIPE_CARD_NUMBER_FIELD, cardNumber);
        } while (isElementVisibleOnFrame(STRIPE_FRAME, CARD_ERROR));
        details.put("CardNumber", cardNumber);

        //Insert Expiration Date
        waitForElement(CARD_NUMBER, 3);
        LocalDate futureDate = LocalDate.now().plusYears(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
        String formattedDate = futureDate.format(formatter);
        do {
            fillElementOnFrame(STRIPE_FRAME, STRIPE_EXPIRATION_DATE_FIELD, formattedDate);
        } while(isElementVisibleOnFrame(STRIPE_FRAME, EXPIRY_ERROR));
        details.put("ExpiryDate", formattedDate);

        //Insert Security Code
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < 3) {
            uniqueNumbers.add(ThreadLocalRandom.current().nextInt(0, 9));
        }
        String securityCode = String.valueOf(uniqueNumbers).replaceAll("[^0-9]", "");
        System.out.println(securityCode);
        fillElementOnFrame(STRIPE_FRAME, STRIPE_SECURITY_CODE_FIELD, securityCode);

        return details;
    }

    @Override
    public void clickSaveAndContinue() {
        clickElement(SAVE_CONTINUE_PAY_BUTTON);
    }

    @Override
    public void clickPayNow() {
        clickSaveAndContinue();
    }
}
