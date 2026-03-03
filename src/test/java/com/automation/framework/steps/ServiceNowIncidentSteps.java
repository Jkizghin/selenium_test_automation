package com.automation.framework.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import com.automation.framework.base.DriverFactory;
import com.automation.framework.pages.ServiceNowIncidentPage;

public class ServiceNowIncidentSteps {

private WebDriver driver;
private ServiceNowIncidentPage incidentPage;

public ServiceNowIncidentSteps() {
driver = DriverFactory.getDriver();
incidentPage = new ServiceNowIncidentPage(driver);
}

@When("I navigate to incident page")
public void i_navigate_to_incident_page() {
incidentPage.goToCreateNewIncident();
System.out.println("=== INCIDENT: Navigated to Create New Incident URL ===");
}

@Then("I should see incident page loaded")
public void i_should_see_incident_page_loaded() {
incidentPage.waitForCreateNewIncidentForm();
System.out.println("=== INCIDENT: Create New Incident form is loaded ===");
}
}