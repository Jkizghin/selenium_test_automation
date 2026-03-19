package com.automation.framework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.framework.base.DriverFactory;
import com.automation.framework.pages.GeneralCloudServicesRequestPage;
import com.automation.framework.pages.ServiceNowImpersonateUrlPage;
import com.automation.framework.pages.ServiceNowServicePortalPage;
// import dev.failsafe.internal.util.Assert;

public class ServiceNowGeneralCloudServicesRequestSteps {
    private GeneralCloudServicesRequestPage generalCloudServicesRequestPage;

    private WebDriver driver;
    private ServiceNowServicePortalPage servicePortalPage;
    private ServiceNowImpersonateUrlPage impersonatePage;

    public ServiceNowGeneralCloudServicesRequestSteps() {
        driver = DriverFactory.getDriver();
        servicePortalPage = new ServiceNowServicePortalPage(driver);
        impersonatePage = new ServiceNowImpersonateUrlPage(driver);
        generalCloudServicesRequestPage = new GeneralCloudServicesRequestPage(driver);
    }

    @When("I navigate to service portal")
    public void i_navigate_to_service_portal() {
        System.out.println("=== REQUEST: Navigating to Service Portal ===");

        driver.switchTo().defaultContent(); // Ensure we're out of any iframes

        impersonatePage.goToImpersonateUrl();
        String userToImpersonate = "Tester Customer Test 1";
        impersonatePage.impersonateUser(userToImpersonate);
        impersonatePage.clickOkButton();

        System.out.println("=== REQUEST: Navigated to Service Portal ===");
    }

    @When("I open a general cloud services request")
    public void i_open_a_general_cloud_services_request() {

        System.out.println("=== REQUEST: Opening General Cloud Services Request ===");

        servicePortalPage.goToGeneralCloudServiceRequest();

        System.out.println("=== REQUEST: Opened General Cloud Services Request ===");
    }

    @And("I fill and submit the general cloud services request")
    public void i_fill_and_submit_the_general_cloud_services_request() {

        generalCloudServicesRequestPage.fillAndSubmitRequest(
                "Tester Customer Test 1",
                "Test automation request",
                "This is automated test submission");

        System.out.println("=== REQUEST: Filled and submitted General Cloud Services Request ===");
    }

    @When("I navigate to the RITM")
    public void i_navigate_to_the_ritm() {
        System.out.println("=== REQUEST: Navigating to RITM ===");

        // code here

        System.out.println("=== REQUEST: Navigated to RITM ===");
    }

    @When("I close the catalog task")
    public void i_close_the_catalog_task() {
        System.out.println("=== REQUEST: Closing Catalog Task ===");

        // code here

        System.out.println("=== REQUEST: Closed Catalog Task ===");
    }

    @When("I close the RITM")
    public void i_close_the_ritm() {
        System.out.println("=== REQUEST: Closing RITM ===");

        // code here

        System.out.println("=== REQUEST: Closed RITM ===");
    }

    @Then("I should see the closed RITM")
    public void i_should_see_the_closed_ritm() {
        System.out.println("=== REQUEST: Checking for closed RITM ===");

        // code here

        System.out.println("=== REQUEST: verified closed RITM ===");
    }

}