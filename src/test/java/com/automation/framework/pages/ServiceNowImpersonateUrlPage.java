package com.automation.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.automation.framework.base.UiActions;
import com.automation.framework.locators.ServiceNowImpersonateUrlLocators;

public class ServiceNowImpersonateUrlPage extends UiActions {

    private static final String IMPERSONATE_URL = "https://gditsharedtest.servicenowservices.com/impersonate_dialog";

    public ServiceNowImpersonateUrlPage(WebDriver driver) {
        super(driver);
    }
    // ---------------- Public API ----------------

    /** Navigate to Impersonate page and wait until it is loaded. */
    public void goToImpersonateUrl() {
       //add 5 seconds wait before navigating to impersonate URL to avoid ServiceNow async loading
        smallWait(5000);


        driver.get(IMPERSONATE_URL);
        waitForPageReady();

        // IMPORTANT: reset context before switching
        driver.switchTo().defaultContent();

        System.out.println("[SN] Switched into default content");

        waitForImpersonateUrlToLoad();

        System.out.println("[SN] Create New Incident page loaded. Title=" + safeTitle());
        System.out.println("[SN] URL=" + driver.getCurrentUrl());
    }

    public void clickOkButton() {
        click(ServiceNowImpersonateUrlLocators.IMPERSPONATE_OK_BUTTON);
        System.out.println("[SN] OK button clicked.");
    }

    public void impersonateUser(String user) {

        // Set user to impersonate
        typeAndSelect(ServiceNowImpersonateUrlLocators.IMPERSONATE_USER, user);
        smallWait(1000);

        System.out.println("[SN] Impersonate user entered: " + user);
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
    public void waitForImpersonateUrlToLoad() {

        System.out.println("[SN] Waiting for impersonate URL to load");

        wait.until(ExpectedConditions.presenceOfElementLocated(
                ServiceNowImpersonateUrlLocators.IMPERSONATE_USER));

        wait.until(ExpectedConditions.elementToBeClickable(
                ServiceNowImpersonateUrlLocators.IMPERSONATE_USER));

        System.out.println("[SN] Impersonate user field detected");
    }

}