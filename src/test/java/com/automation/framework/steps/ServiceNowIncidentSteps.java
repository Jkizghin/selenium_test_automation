package com.automation.framework.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.framework.base.DriverFactory;
import com.automation.framework.locators.ServiceNowCreateIncidentLocators;
import com.automation.framework.pages.ServiceNowIncidentPage;
import com.automation.framework.pages.ServiceNowImpersonateUrlPage;
import dev.failsafe.internal.util.Assert;

public class ServiceNowIncidentSteps {

    private WebDriver driver;
    private ServiceNowIncidentPage incidentPage;
    private ServiceNowImpersonateUrlPage impersonatePage;

    public ServiceNowIncidentSteps() {
        driver = DriverFactory.getDriver();
        incidentPage = new ServiceNowIncidentPage(driver);
        impersonatePage = new ServiceNowImpersonateUrlPage(driver);
    }

    @When("I navigate to incident page")
    public void i_navigate_to_incident_page() {
        // add 5 second wait
        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        incidentPage.goToCreateNewIncident();
        System.out.println("=== INCIDENT: Navigated to Create New Incident ===");
    }

    @When("I enter incident values")
    public void enter_incident_values() {
        System.out.println("=== Incident Creation: enter values ===");

        // New values defined variables
        String affectedUser = "Tester Customer Test 1"; // or "" if you don't want to set it
        String category = "Failure";
        String subcategory = "Security - Software";
        String shortDesc = "Selenium Test - Test Short Description";
        String description = "Created by Selenium automation";

        incidentPage.populateIncident(affectedUser, category, subcategory, shortDesc, description);

        incidentPage.clickSaveButton();
        incidentPage.waitForIncidentFormToLoad();
        System.out.println("=== Incident Creation: values entered ===");

        // In Progress values defined variables
        String state = "In Progress";
        String assignementGroup = "TSS Applications - ServiceNow Support - Tier 1";
        String assignedTo = "Tester ACDaaS_Fulfiller_T3";

        incidentPage.updateIncidentAssignment(state, assignementGroup, assignedTo);

        incidentPage.waitForIncidentFormToLoad();
        incidentPage.clickSaveButtonAfterUpdate();
        incidentPage.waitForIncidentFormToLoad();

        System.out.println("=== Incident updated to In Progress and assigned ===");

        // Resolved values defined variables
        String resolvedState = "Resolved";
        String resolutionCode = "Solved (Work Around)";
        String resolutionNotes = "Selenium Test - Test Resolution notes";

        incidentPage.resolveIncident(resolvedState, resolutionCode, resolutionNotes);

        incidentPage.waitForIncidentFormToLoad();
        incidentPage.clickSaveButtonAfterUpdate();
        incidentPage.waitForIncidentFormToLoad();

        System.out.println("=== Incident resolved and saved ===");

    }

    @When("I close the incident")
    public void i_close_the_incident() {

        System.out.println("=== INCIDENT: Closing incident ===");
        String incidentUrl = driver.getCurrentUrl();
        System.out.println("=== Incident URL: " + incidentUrl + " ===");

        driver.switchTo().defaultContent(); // Ensure we're out of any iframes

        impersonatePage.goToImpersonateUrl();
        String userToImpersonate = "Tester Supervisor Test 1"; // User that has permissions to close the incident
        impersonatePage.impersonateUser(userToImpersonate);
        impersonatePage.clickOkButton();

        incidentPage.navigateBackToIncident(incidentUrl); // Navigate back to the incident URL
        incidentPage.waitForIncidentFormToLoad();

        incidentPage.clickCloseIncident();

        // end impersonation - back to normal user
        impersonatePage.goToImpersonateUrl();
        String seleniumUser = "GDIT Selenium Test Automation User";
        impersonatePage.impersonateUser(seleniumUser);
        impersonatePage.clickOkButton();

        incidentPage.navigateBackToIncident(incidentUrl); // Navigate back to the incident URL
        incidentPage.waitForIncidentFormToLoadNonClickable();

    }

    @Then("I should see the closed incident")
    public void i_should_see_the_closed_incident() {

        System.out.println("=== INCIDENT page loaded successfully (State: Closed) ===");
        // Add verification steps here to check that the incident is closed
        WebElement description2 = driver.findElement(ServiceNowCreateIncidentLocators.DESCRIPTION);
        System.out.println("description writeaccess attribute value: " + description2.getAttribute("writeaccess"));
        if (description2.getAttribute("writeaccess").equals("false")) {
            System.out.println("=== Incident is closed: description field is readonly ===");
        } else {
            System.out.println("=== Incident is not closed or description field is not readonly ===");
        }
        Assert.isTrue(description2.getAttribute("writeaccess").equals("false"),
                "Incident is not closed or description field is not readonly");
    }
}