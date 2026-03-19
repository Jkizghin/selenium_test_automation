package com.automation.framework.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.automation.framework.base.UiActions;
import com.automation.framework.locators.ServiceNowCreateIncidentLocators;
import com.automation.framework.locators.ServiceNowServicePortalLocators;

public class ServiceNowServicePortalPage extends UiActions {

    public ServiceNowServicePortalPage(WebDriver driver) {
        super(driver);
    }
    // ---------------- Public API ----------------

    /** Navigate to Create New Incident page and wait until it is loaded. */
    public void goToGeneralCloudServiceRequest() {

        System.out.println("[SP] Navigating to General Cloud Services Request page");

        waitForServicePortalToLoad();

        // Click "Request Something"
        click(ServiceNowServicePortalLocators.REQUEST_SOMETHING);
        System.out.println("[SP] Clicked Request Something");
        // Click "Cloud Services" link to navigate to general cloud services
        clickCloudServicesLink();
        clickGeneralCloudServicesRequest();
        System.out.println("[SP] General Cloud Services Request page loaded");
    }

    /** Navigate to Cloud Services by clicking on the Cloud Services link. */

    public void clickCloudServicesLink() {
        System.out.println("[SP] Navigating to Cloud Services");

        try {
            WebElement el = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            ServiceNowServicePortalLocators.CLOUD_SERVICES));

            System.out.println("[SP] Cloud Services span found");
            System.out.println("[SP] Displayed: " + el.isDisplayed());
            System.out.println("[SP] Enabled: " + el.isEnabled());

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    el);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    el);

            System.out.println("[SP] Cloud Services clicked");

            waitForPageReady();
            System.out.println("[SP] Cloud Services page loaded");

        } catch (Exception e) {
            System.out.println("[SP] ERROR TYPE: " + e.getClass().getName());
            System.out.println("[SP] ERROR MESSAGE: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Could not click Cloud Services link", e);
        }
    }
    // Click General Cloud Services Request link to navigate to the request page

    public void clickGeneralCloudServicesRequest() {
        System.out.println("[SP] Opening General Cloud Services Request");

        try {
            WebElement el = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            ServiceNowServicePortalLocators.GENERAL_CLOUD_SERVICES_REQUEST));

            System.out.println("[SP] General Cloud Services Request found");
            System.out.println("[SP] Displayed: " + el.isDisplayed());
            System.out.println("[SP] Enabled: " + el.isEnabled());

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    el);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    el);

            System.out.println("[SP] General Cloud Services Request clicked");

            waitForPageReady();

        } catch (Exception e) {
            System.out.println("[SP] ERROR TYPE: " + e.getClass().getName());
            System.out.println("[SP] ERROR MESSAGE: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Could not click General Cloud Services Request", e);
        }
    }

    // ---------------- Core helpers ----------------

    private void waitForPageReady() {
        try {
            wait.until(d -> "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
        } catch (Exception ignored) {
            // don’t fail on this; ServiceNow can keep async loading even after
            // readyState=complete
        }
    }

    /**
     * Wait until we can see a form container OR a known field, in any context.
     * This is the “main gate” before trying to interact with fields.
     */
    public void waitForServicePortalToLoad() {

        System.out.println("[SP] Waiting for Service Portal to load");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                ServiceNowServicePortalLocators.REQUEST_SOMETHING));

        wait.until(ExpectedConditions.elementToBeClickable(
                ServiceNowServicePortalLocators.REQUEST_SOMETHING));

        System.out.println("[SP] Service Portal detected");
    }
}
