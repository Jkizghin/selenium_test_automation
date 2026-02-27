package com.automation.framework.steps;

import com.automation.framework.base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ServiceNowSteps {

@Given("I open the browser")
public void i_open_the_browser() {
DriverFactory.createDriver();
System.out.println("===== OPEN STEP DONE =====");
}

@When("I navigate to {string}")
public void i_navigate_to(String url) {
WebDriver driver = DriverFactory.getDriver();

System.out.println("Navigating to: " + url);
driver.get(url);

System.out.println("===== NAV STEP FINISHED =====");
}

@Then("the page title should contain {string}")
public void the_page_title_should_contain(String expectedTitle) {
WebDriver driver = DriverFactory.getDriver();

String actualTitle = driver.getTitle();

System.out.println("Actual title is: " + actualTitle);
System.out.println("Expected to contain: " + expectedTitle);

Assert.assertTrue(
actualTitle.contains(expectedTitle),
"Title does not contain expected text."
);
}

@Then("I wait {int} seconds")
public void i_wait_seconds(int seconds) throws InterruptedException {
System.out.println("Waiting for " + seconds + " seconds...");
Thread.sleep(seconds * 1000L);
}
}