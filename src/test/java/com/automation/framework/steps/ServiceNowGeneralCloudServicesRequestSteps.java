package com.automation.framework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.framework.base.DriverFactory;
import com.automation.framework.pages.GeneralCloudServicesRequestPage;
import com.automation.framework.pages.ServiceNowHomePage;
import com.automation.framework.pages.ServiceNowImpersonateUrlPage;
import com.automation.framework.pages.ServiceNowServicePortalPage;
import com.automation.framework.pages.ServiceNowRitmPage;
// import dev.failsafe.internal.util.Assert;

public class ServiceNowGeneralCloudServicesRequestSteps {

    private WebDriver driver;
    private ServiceNowServicePortalPage servicePortalPage;
    private ServiceNowImpersonateUrlPage impersonatePage;
    private GeneralCloudServicesRequestPage generalCloudServicesRequestPage;
    private ServiceNowHomePage serviceNowHomePage;
    private ServiceNowRitmPage serviceNowRitmPage;

    public ServiceNowGeneralCloudServicesRequestSteps() {
        driver = DriverFactory.getDriver();
        servicePortalPage = new ServiceNowServicePortalPage(driver);
        impersonatePage = new ServiceNowImpersonateUrlPage(driver);
        generalCloudServicesRequestPage = new GeneralCloudServicesRequestPage(driver);
        serviceNowHomePage = new ServiceNowHomePage(driver);
        serviceNowRitmPage = new ServiceNowRitmPage(driver);
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
                "Selenium Test - Test short description",
                "Selenium Test - Test additional comments");
        String createdRitm = generalCloudServicesRequestPage.getCreatedRitmNumber();
        System.out.println("[SP] Captured RITM: " + createdRitm);
 
        System.out.println("=== REQUEST: Filled and submitted General Cloud Services Request ===");
    }

    @When("I navigate to the RITM")
    public void i_navigate_to_the_ritm() {
        System.out.println("=== REQUEST: Navigating to RITM ===");

        // code here
        String createdRitm = generalCloudServicesRequestPage.getCreatedRitmNumber();
        System.out.println("[SP] Captured RITM2: " + createdRitm);

        driver.switchTo().defaultContent(); // Ensure we're out of any iframes

        impersonatePage.goToImpersonateUrl();
        String endImpersonation = "GDIT Selenium Test Automation User";
        impersonatePage.impersonateUser(endImpersonation);
        impersonatePage.clickOkButton();

        impersonatePage.goToImpersonateUrl();
        String userToImpersonate = "Tester Specialist Test 1";
        impersonatePage.impersonateUser(userToImpersonate);
        impersonatePage.clickOkButton();

        // Search for the RITM in the search bar
        serviceNowHomePage.searchGlobalBar(createdRitm);

        /**
         * TODO: add form validation
         * - variables are non-editable
         * - assignment group CloudFinOps
         */

        System.out.println("=== REQUEST: Navigated to RITM ===");
    }

    @When("I close the catalog task")
    public void i_close_the_catalog_task() {
        System.out.println("=== REQUEST: Closing Catalog Task ===");

        serviceNowRitmPage.waitForRitmPageToLoad();

        // update state to "Work in Progress" and save
        serviceNowRitmPage.updateState("Work in Progress");
        serviceNowRitmPage.clickSaveButtonAfterUpdate();

        /**
         * TODO: open sctask
         * state: Work in Progress
         * save
         * add work notes: Selenium Test - Test Work notes
         * post
         * close task
         */

        System.out.println("=== REQUEST: Closed Catalog Task ===");
    }

    @When("I close the RITM")
    public void i_close_the_ritm() {
        System.out.println("=== REQUEST: Closing RITM ===");

        // state should be Closed Complete

        System.out.println("=== REQUEST: Closed RITM ===");
    }

    @Then("I should see the closed RITM")
    public void i_should_see_the_closed_ritm() {
        System.out.println("=== REQUEST: Checking for closed RITM ===");

        // verify RITM is closed

        System.out.println("=== REQUEST: verified closed RITM ===");
    }

}