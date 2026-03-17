package com.automation.framework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", 
        glue = "com.automation.framework", 
        tags = "@generalCloudServicesRequest",
        monochrome = true, 
        plugin = {
        "pretty",
        "html:target/cucumber-report/cucumber.html",
        "json:target/cucumber-report/cucumber.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
})
public class TestRunner extends AbstractTestNGCucumberTests {
}