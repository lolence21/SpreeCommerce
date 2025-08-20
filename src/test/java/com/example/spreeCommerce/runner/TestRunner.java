package com.example.spreeCommerce.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com/example/spreeCommerce/steps"},
        tags = "@spree-commerce-test",
        plugin = {"pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
