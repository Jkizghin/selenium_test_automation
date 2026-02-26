package com.automation.framework.steps;

import com.automation.framework.base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GoogleSteps {

@Given("I open the browser")
public void i_open_the_browser() {
DriverFactory.createDriver();
System.out.println("===== OPEN STEP DONE =====");
}

@When("I navigate to {string}")
public void i_navigate_to(String url) {
System.out.println("===== NAV STEP STARTED =====");

WebDriver driver = DriverFactory.getDriver();
System.out.println("Navigating to: " + url);

driver.get(url);

System.out.println("===== NAV STEP FINISHED =====");
}

@Then("the page title should contain {string}")
public void the_page_title_should_contain(String expectedTitle) {
WebDriver driver = DriverFactory.getDriver();
String title = driver.getTitle();
System.out.println("TITLE: " + title);
Assert.assertTrue(title.contains(expectedTitle), "Title does not contain expected text");
}
}