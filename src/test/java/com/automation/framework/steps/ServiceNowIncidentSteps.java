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
        System.out.println("=== INCIDENT: Navigated to Create New Incident ===");
    }

    @When("I enter incident values")
    public void enter_incident_values() {
        System.out.println("=== Incident Creation: enter values ===");

        // TODO: adjust values as you want
        String affectedUser = "Tester Customer Test 1"; // or "" if you don't want to set it
        String category = "Failure";
        String subcategory = "Security - Software";
        String shortDesc = "Selenium Test - Test Short Description";
        String description = "Created by Selenium automation";

        incidentPage.populateIncident(affectedUser, category, subcategory, shortDesc, description);

        incidentPage.clickSaveButton();
        incidentPage.waitForIncidentFormToLoad();
        System.out.println("=== Incident Creation: values entered ===");

        // asignement values defined variables
        String state = "In Progress";
        String assignementGroup = "TSS Applications - ServiceNow Support - Tier 1";
        String assignedTo = "Tester ACDaaS_Fulfiller_T3";

        incidentPage.updateIncidentAssignment(state, assignementGroup, assignedTo);

        incidentPage.waitForIncidentFormToLoad();
        incidentPage.clickSaveButtonAfterUpdate();
        incidentPage.waitForIncidentFormToLoad();

        System.out.println("=== Incident updated to In Progress and assigned ===");

        //Resolved values defined variables
        String resolvedState = "Resolved";
        String resolutionCode = "Solved (Work Around)";
        String resolutionNotes = "Selenium Test - Test Resolution notes";

        incidentPage.resolveIncident(resolvedState, resolutionCode, resolutionNotes);

        incidentPage.waitForIncidentFormToLoad();
        incidentPage.clickSaveButtonAfterUpdate();
        incidentPage.waitForIncidentFormToLoad();

        System.out.println("=== Incident resolved and saved ===");

    }

    @Then("I should see incident page loaded")
    public void i_should_see_incident_page_loaded() {

        // This should wait for the form field inside gsft_main (your real “page loaded”
        // check)
        incidentPage.waitForIncidentFormToLoad();

        System.out.println("=== INCIDENT page loaded successfully ===");
    }
}