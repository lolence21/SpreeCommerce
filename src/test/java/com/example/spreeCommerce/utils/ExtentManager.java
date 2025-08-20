package com.example.spreeCommerce.utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportFolderPath;

    public static void initReport() {
        // Generate timestamp for unique folder
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        reportFolderPath = "reports/Run_" + timestamp;

        // Create directories
        new File(reportFolderPath + "/screenshots");

        // Set report file path
        ExtentSparkReporter spark = new ExtentSparkReporter(reportFolderPath +".html");
        spark.config().setReportName("SpreeCommerce Automation Report");
        spark.config().setDocumentTitle("Test Results");

        // Initialize Extent
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static String getReportFolderPath(){
        return reportFolderPath;
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flushReport() {
        extent.flush();
    }
}
