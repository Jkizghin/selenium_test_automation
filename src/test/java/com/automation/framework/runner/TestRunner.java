package com.automation.framework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = "com.automation.framework", plugin = { "pretty",
        "html:target/cucumber-reports.html" }, monochrome = true, tags = "@servicenow_auth")
public class TestRunner extends AbstractTestNGCucumberTests {
}