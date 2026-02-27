package com.automation.framework.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.framework.base.DriverFactory;
import com.automation.framework.pages.ServiceNowAuthenticationPage;

public class ServiceNowAuthenticationSteps {

    private WebDriver driver;
    private ServiceNowAuthenticationPage authPage;

    @Given("I open the browser for authentication")
    public void open_browser() {
        DriverFactory.createDriver();
        driver = DriverFactory.getDriver();
        authPage = new ServiceNowAuthenticationPage(driver);
        System.out.println("=== AUTH: Browser opened ===");
    }

    @When("I navigate to authentication url {string}")
    public void navigate_to_auth_url(String url) {
        driver.get(url);
        System.out.println("=== AUTH: Navigated to " + url + " ===");
    }

    @When("I enter authentication username {string} and password {string}")
    public void enter_credentials(String username, String password) {
        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        authPage.login(username, password);
        System.out.println("=== AUTH: Credentials entered ===");
        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("I should be authenticated")
    public void verify_authentication() {

        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // boolean loggedIn = !driver.getCurrentUrl().toLowerCase().contains("login");
        boolean notLoggedIn = driver.getCurrentUrl().toLowerCase()
                .equals("https://gditsharedtest.servicenowservices.com/gdit_tss?id=gdit_tss_landing");

        System.out.println("=== AUTH: Current URL === " + driver.getCurrentUrl());

        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Assert.assertTrue(loggedIn, "Authentication failed — still on login page.");
        Assert.assertFalse(notLoggedIn, "Authentication failed — still on login page.");
    }
}
