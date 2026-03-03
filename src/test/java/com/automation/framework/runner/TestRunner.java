package com.automation.framework.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", 
        glue = "com.automation.framework", 
        tags = "@incident", 
        monochrome = true, 
        plugin = {
        "pretty",
        "html:target/cucumber-report/cucumber.html",
        "json:target/cucumber-report/cucumber.json"
})
public class TestRunner extends AbstractTestNGCucumberTests {
}