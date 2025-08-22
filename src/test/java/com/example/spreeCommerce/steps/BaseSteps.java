package com.example.spreeCommerce.steps;

import com.aventstack.extentreports.Status;
import com.example.spreeCommerce.utils.ExtentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class BaseSteps {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void assertEqualsAndLog(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected, expected + " is not equal to " + actual);
            passLogger(expected);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(expected + " is not equal to " + actual + " " + e);
        }
    }

    protected void assertEqualsAndLog(Double actual, Double expected) {
        try {
            Assert.assertEquals(actual, expected, expected + " is not equal to " + actual);
            passLogger(expected);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(expected + " is not equal to " + actual + " " + e);
        }
    }

    protected void assertTrueAndLog(boolean bool, String passMessage, String failMessage) {
        try {
            Assert.assertTrue(bool, failMessage);
            passLogger(passMessage);
        } catch (AssertionError | Exception e) {
            stackTraceLogger(failMessage + " " + e);
        }
    }

    protected void stackTraceLogger(String errorMessage) {
        String log = Status.FAIL + " - " + errorMessage;
        logger.error(log);
        ExtentManager.getTest().fail(log);
        Assert.fail(log);
    }

    protected void passLogger(Object passMessage) {
        ExtentManager.getTest().log(Status.PASS, passMessage.toString());
        logger.info("{} - {}", passMessage, Status.PASS);
    }

    public static Double priceStringtoDouble(String value) {
        return Double.parseDouble(value.replaceAll("[^0-9.-]", ""));
    }
}
