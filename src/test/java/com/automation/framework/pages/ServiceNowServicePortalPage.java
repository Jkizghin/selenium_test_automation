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

        // TODO: add more navigation steps here to get to the actual request form
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