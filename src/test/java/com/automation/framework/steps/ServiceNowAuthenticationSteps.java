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
authPage.login(username, password);
System.out.println("=== AUTH: Credentials entered ===");
}

@Then("I should be authenticated")
public void verify_authentication() {

boolean loggedIn = !driver.getCurrentUrl().toLowerCase().contains("login");

System.out.println("=== AUTH: Current URL === " + driver.getCurrentUrl());

Assert.assertTrue(loggedIn, "Authentication failed — still on login page.");
}
}
