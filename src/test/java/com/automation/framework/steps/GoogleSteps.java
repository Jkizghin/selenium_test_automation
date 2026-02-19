package com.automation.framework.steps;

import com.automation.framework.base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GoogleSteps {

    WebDriver driver;

    @Given("I open the browser")
    public void i_open_the_browser() {
        driver = DriverFactory.createDriver();
    }

    @When("I navigate to {string}")
    public void i_navigate_to(String url) {
        driver.navigate().to(url);
    }

    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitle) {

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());

        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Title does not contain expected text");

        DriverFactory.quitDriver();
    }
}